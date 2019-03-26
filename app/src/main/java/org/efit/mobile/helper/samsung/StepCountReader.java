/**
 * Copyright (C) 2014 Samsung Electronics Co., Ltd. All rights reserved.
 * <p>
 * Mobile Communication Division,
 * Digital Media & Communications Business, Samsung Electronics Co., Ltd.
 * <p>
 * This software and its documentation are confidential and proprietary
 * information of Samsung Electronics Co., Ltd.  No part of the software and
 * documents may be copied, reproduced, transmitted, translated, or reduced to
 * any electronic medium or machine-readable form without the prior written
 * consent of Samsung Electronics.
 * <p>
 * Samsung Electronics makes no representations with respect to the contents,
 * and assumes no responsibility for any errors that might appear in the
 * software and documents. This publication and the contents hereof are subject
 * to change without notice.
 */

package org.efit.mobile.helper.samsung;

import com.samsung.android.sdk.healthdata.HealthConstants;
import com.samsung.android.sdk.healthdata.HealthData;
import com.samsung.android.sdk.healthdata.HealthDataResolver;
import com.samsung.android.sdk.healthdata.HealthDataResolver.AggregateRequest;
import com.samsung.android.sdk.healthdata.HealthDataResolver.AggregateRequest.AggregateFunction;
import com.samsung.android.sdk.healthdata.HealthDataResolver.AggregateRequest.TimeGroupUnit;
import com.samsung.android.sdk.healthdata.HealthDataResolver.Filter;
import com.samsung.android.sdk.healthdata.HealthDataResolver.ReadRequest;
import com.samsung.android.sdk.healthdata.HealthDataResolver.SortOrder;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.samsung.android.sdk.healthdata.HealthDataUtil;
import com.samsung.android.sdk.healthdata.HealthResultHolder;

import android.util.Log;

import org.efit.mobile.MainMenuActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class StepCountReader {

    public static final String STEP_SUMMARY_DATA_TYPE_NAME = "com.samsung.shealth.step_daily_trend";
    public static final long TODAY_START_UTC_TIME;
    public static final long ONE_DAY = 24 * 60 * 60 * 1000;

    private static final String PROPERTY_TIME = "day_time";
    private static final String PROPERTY_COUNT = "count";
    private static final String PROPERTY_BINNING_DATA = "binning_data";
    private static final String ALIAS_TOTAL_COUNT = "count";
    private static final String ALIAS_DEVICE_UUID = "deviceuuid";
    private static final String ALIAS_EXERCISE_NAME = "exercise_name";
    private static final String ALIAS_BINNING_TIME = "binning_time";

    private final HealthDataResolver mResolver;
    private final StepCountObserver mObserver;

    static {
        TODAY_START_UTC_TIME = getTodayStartUtcTime();
    }

    private static long getTodayStartUtcTime() {
        Calendar today = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        Log.d(MainMenuActivity.TAG, "Today : " + today.getTimeInMillis());

        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        return today.getTimeInMillis();
    }

    public StepCountReader(HealthDataStore store, StepCountObserver observer) {
        mResolver = new HealthDataResolver(store, null);
        mObserver = observer;
    }

    // Get the daily total step count of a specified day
    public void requestDailyStepCount(long startTime) {
        if (startTime >= TODAY_START_UTC_TIME) {
            // Get today step count
            readStepCount(startTime);
            readExerciseCount(startTime);
            readTodayKaloriCount(startTime);
        } else {
            // Get historical step count
            readStepDailyTrend(startTime);
        }
    }

    private void readStepCount(final long startTime) {
        // Get sum of step counts by device
        AggregateRequest request = new AggregateRequest.Builder()
                .setDataType(HealthConstants.StepCount.HEALTH_DATA_TYPE)
                .addFunction(AggregateFunction.SUM, HealthConstants.StepCount.COUNT, ALIAS_TOTAL_COUNT)
                .addGroup(HealthConstants.StepCount.DEVICE_UUID, ALIAS_DEVICE_UUID)
                .setLocalTimeRange(HealthConstants.StepCount.START_TIME, HealthConstants.StepCount.TIME_OFFSET,
                        startTime, startTime + ONE_DAY)
                .setSort(ALIAS_TOTAL_COUNT, SortOrder.DESC)

                .build();

        try {
            mResolver.aggregate(request).setResultListener(result -> {
                int totalCount = 0;
                String deviceUuid = null;

                try {
                    Iterator<HealthData> iterator = result.iterator();
                    if (iterator.hasNext()) {
                        HealthData data = iterator.next();
                        totalCount = data.getInt(ALIAS_TOTAL_COUNT);
                        deviceUuid = data.getString(ALIAS_DEVICE_UUID);
                    }
                } finally {
                    result.close();
                }
                if (mObserver != null) {
                    mObserver.onChanged(totalCount);
                }
                if (deviceUuid != null) {
                    readStepCountBinning(startTime, deviceUuid);
                }
            });
        } catch (Exception e) {
            Log.e(MainMenuActivity.TAG, "Getting step count fails.", e);
        }
    }

    private void readExerciseCount(final long startTime) {
        // Get sum of step counts by device
        AggregateRequest request = new AggregateRequest.Builder()
                .setDataType(HealthConstants.Exercise.HEALTH_DATA_TYPE)
                .addFunction(AggregateFunction.SUM, HealthConstants.Exercise.CALORIE, ALIAS_TOTAL_COUNT)
                .addGroup(HealthConstants.Exercise.DEVICE_UUID, ALIAS_DEVICE_UUID)
                .addGroup(HealthConstants.Exercise.EXERCISE_TYPE, ALIAS_EXERCISE_NAME)
                .setLocalTimeRange(HealthConstants.Exercise.START_TIME, HealthConstants.Exercise.TIME_OFFSET,
                        startTime, startTime + ONE_DAY)
                .setSort(ALIAS_TOTAL_COUNT, SortOrder.DESC)
                .build();

        try {
            mResolver.aggregate(request).setResultListener(result -> {
                int totalCount = 0;
                String deviceUuid = null;
                int exname = 0;

                try {
                    Iterator<HealthData> iterator = result.iterator();
                    if (iterator.hasNext()) {
                        HealthData data = iterator.next();
                        totalCount = data.getInt(ALIAS_TOTAL_COUNT);
                        deviceUuid = data.getString(ALIAS_DEVICE_UUID);
                        exname = data.getInt(ALIAS_EXERCISE_NAME);

                    }
                } finally {
                    result.close();
                }
                if (mObserver != null) {
                    mObserver.onChangedExercise(totalCount, Integer.toString(exname));
                }
                if (deviceUuid != null) {
//                    readStepCountBinning(startTime, deviceUuid);
                }
            });
        } catch (Exception e) {
            Log.e(MainMenuActivity.TAG, "Getting step count fails.", e);
        }
    }

    private void readTodayKaloriCount(final long startTime) {
        ReadRequest request = new ReadRequest.Builder()
                .setDataType(HealthConstants.Exercise.HEALTH_DATA_TYPE)
                .setProperties(new String[]{HealthConstants.Exercise.CALORIE, HealthConstants.Exercise.EXERCISE_TYPE})
                .setLocalTimeRange(HealthConstants.StepCount.START_TIME, HealthConstants.StepCount.TIME_OFFSET,startTime, startTime + ONE_DAY)
                .build();
        try {
            mResolver.read(request).setResultListener(mListener);
        } catch (Exception e) {
            Log.e(MainMenuActivity.CURRENT_TAG, "Getting step count fails.", e);
        }
    }

    private final HealthResultHolder.ResultListener<HealthDataResolver.ReadResult> mListener = result -> {
        mObserver = null;
        int count = 0;
        int tipe = 0;
        try {
            for (HealthData data : result) {
                count += data.getInt(HealthConstants.Exercise.CALORIE);
                tipe = data.getInt(HealthConstants.Exercise.EXERCISE_TYPE);
            }
        } finally {
            result.close();
        }
        if (mObserver != null) {
            mObserver.onChangedKalori(count, String.valueOf(tipe));
        }
    };

    private void readStepDailyTrend(final long startTime) {

        Filter filter = Filter.and(Filter.eq(PROPERTY_TIME, startTime),
                // filtering source type "combined(-2)"
                Filter.eq("source_type", -2));

        ReadRequest request = new ReadRequest.Builder()
                .setDataType(STEP_SUMMARY_DATA_TYPE_NAME)
                .setProperties(new String[]{PROPERTY_COUNT, PROPERTY_BINNING_DATA})
                .setFilter(filter)
                .build();

        try {
            mResolver.read(request).setResultListener(result -> {
                int totalCount = 0;
                List<StepBinningData> binningDataList = Collections.emptyList();

                try {
                    Iterator<HealthData> iterator = result.iterator();
                    if (iterator.hasNext()) {
                        HealthData data = iterator.next();
                        totalCount = data.getInt(PROPERTY_COUNT);
                        byte[] binningData = data.getBlob(PROPERTY_BINNING_DATA);
                        binningDataList = getBinningData(binningData);
                    }
                } finally {
                    result.close();
                }

                if (mObserver != null) {
                    mObserver.onChanged(totalCount);
//                    mObserver.onBinningDataChanged(binningDataList);
                }

            });
        } catch (Exception e) {
            Log.e(MainMenuActivity.TAG, "Getting daily step trend fails.", e);
        }
    }
    private void readStepCountBinning(final long startTime, String deviceUuid) {

        Filter filter = Filter.eq(HealthConstants.StepCount.DEVICE_UUID, deviceUuid);

        // Get 10 minute binning data of a particular device
        AggregateRequest request = new AggregateRequest.Builder()
                .setDataType(HealthConstants.StepCount.HEALTH_DATA_TYPE)
                .addFunction(AggregateFunction.SUM, HealthConstants.StepCount.COUNT, ALIAS_TOTAL_COUNT)
                .setTimeGroup(TimeGroupUnit.MINUTELY, 10, HealthConstants.StepCount.START_TIME,
                        HealthConstants.StepCount.TIME_OFFSET, ALIAS_BINNING_TIME)
                .setLocalTimeRange(HealthConstants.StepCount.START_TIME, HealthConstants.StepCount.TIME_OFFSET,
                        startTime, startTime + ONE_DAY)
                .setFilter(filter)
                .setSort(ALIAS_BINNING_TIME, SortOrder.ASC)
                .build();

        try {
            mResolver.aggregate(request).setResultListener(result -> {

                List<StepBinningData> binningCountArray = new ArrayList<>();

                try {
                    for (HealthData data : result) {
                        String binningTime = data.getString(ALIAS_BINNING_TIME);
                        int binningCount = data.getInt(ALIAS_TOTAL_COUNT);

                        if (binningTime != null) {
                            binningCountArray.add(new StepBinningData(binningTime.split(" ")[1], binningCount));
                        }
                    }

                    if (mObserver != null) {
                        mObserver.onBinningDataChanged(binningCountArray);
                    }

                } finally {
                    result.close();
                }
            });
        } catch (Exception e) {
            Log.e(MainMenuActivity.TAG, "Getting step binning data fails.", e);
        }
    }

    private static List<StepBinningData> getBinningData(byte[] zip) {
        // decompress ZIP
        List<StepBinningData> binningDataList = HealthDataUtil.getStructuredDataList(zip, StepBinningData.class);
        for (int i = binningDataList.size() - 1; i >= 0; i--) {
            if (binningDataList.get(i).count == 0) {
                binningDataList.remove(i);
            } else {
                binningDataList.get(i).time = String.format(Locale.US, "%02d:%02d", i / 6, (i % 6) * 10);
            }
        }

        return binningDataList;
    }

    public static class StepBinningData {
        public String time;
        public final int count;

        public StepBinningData(String time, int count) {
            this.time = time;
            this.count = count;
        }
    }

    public interface StepCountObserver {
        void onChanged(int count);
        void onChangedExercise(int count, String perangkat);
        void onChangedKalori(int count, String perangkat);

        void onBinningDataChanged(List<StepBinningData> binningCountList);
    }

}
