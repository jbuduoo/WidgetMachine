package com.worldline.interview;

/**
 * SteamEngine 類別繼承自 Engine，用於模擬蒸汽引擎的行為。
 * 支援的燃料類型為木材（WOOD）和煤炭（COAL），並具有特定的成本計算和批次大小設定。
 */
public class SteamEngine extends Engine {

    // 蒸汽引擎所需的燃料類型（WOOD 或 COAL）
    private FuelType requiredFuelType;

    /**
     * 填充燃料的方法，確保填充的燃料類型有效（僅支援 WOOD 和 COAL），並調整燃料量至有效範圍（0-100）。
     *
     * @param fuelType  填充的燃料類型
     * @param fuelLevel 填充的燃料量
     * @throws IllegalArgumentException 當燃料類型無效時拋出
     */
    @Override
    public void fill(FuelType fuelType, int fuelLevel) {
        // 驗證燃料類型是否有效
        if (fuelType != FuelType.WOOD && fuelType != FuelType.COAL) {
            throw new IllegalArgumentException("Invalid fuel type for SteamEngine.");
        }
        // 調整燃料量至有效範圍
        if (fuelLevel < 0) {
            this.fuelLevel = 0;
        } else if (fuelLevel > 100) {
            this.fuelLevel = 100;
        } else {
            this.fuelLevel = fuelLevel;
        }
        this.fuelType = fuelType; // 設置當前燃料類型
    }

    /**
     * 獲取蒸汽引擎所需的燃料類型。
     *
     * @return 所需的燃料類型（WOOD 或 COAL）
     */
    public FuelType getFuelType() {
        return requiredFuelType;
    }

    /**
     * SteamEngine 的建構子，用於初始化所需燃料類型和批次大小。
     *
     * @param requiredFuelType 蒸汽引擎所需的燃料類型
     * @throws IllegalStateException 當傳入的燃料類型無效時拋出
     */
    public SteamEngine(FuelType requiredFuelType) {
        super(requiredFuelType); // 呼叫父類別的建構子
        // 驗證燃料類型是否為 WOOD 或 COAL
        if (requiredFuelType == FuelType.WOOD || requiredFuelType == FuelType.COAL) {
            this.requiredFuelType = requiredFuelType;
        } else {
            throw new IllegalStateException("Wrong FuelType");
        }

        // 設置蒸汽引擎的批次大小為 2
        this.batchSize = 2;
    }

    /**
     * 計算每批次的成本，根據當前使用的燃料類型決定成本。
     *
     * @return 每批次的成本（WOOD 為 4.35，COAL 為 5.65）
     */
    @Override
    public double getCostPerBatch() {
        return this.fuelType == FuelType.WOOD ? 4.35 : 5.65;
    }
}
