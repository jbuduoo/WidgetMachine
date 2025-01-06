package com.worldline.interview;

/**
 * Engine 類別是一個通用引擎的抽象實現，提供啟動、停止、加注燃料等基本功能。
 * 子類別（例如 InternalCombustionEngine 和 SteamEngine）可繼承該類並擴展具體行為。
 */
public class Engine {
    // 引擎是否正在運行
    protected boolean running;

    // 當前燃料量（0-8）
    protected int fuelLevel;

    // 引擎需要的燃料類型（如汽油、柴油、木材或煤炭）
    protected FuelType requiredFuelType;

    // 當前引擎填充的燃料類型
    protected FuelType fuelType;

    // 每批次的成本
    protected double getCostPerBatch;

    // 每批次的大小
    protected int batchSize;

    /**
     * 無參數建構子（默認建構子）。
     * 可由子類別顯式或隱式調用。
     */
    public Engine() {
        super();
    }

    /**
     * 帶參數建構子，用於初始化引擎所需的燃料類型。
     * @param requiredFuelType 引擎運行所需的燃料類型
     */
    public Engine(FuelType requiredFuelType) {
        this.requiredFuelType = requiredFuelType;
        this.running = false; // 初始化時引擎為關閉狀態
        this.fuelLevel = 0;   // 初始化時燃料量為 0
        this.batchSize = 0;   // 默認批次大小為 0
    }

    /**
     * 啟動引擎。
     * 如果燃料量大於 0 且當前燃料類型與所需燃料類型一致，引擎將啟動。
     * 否則拋出 IllegalStateException。
     */
    public void start() {
        if (fuelLevel > 0 && requiredFuelType.equals(fuelType)) {
            running = true;
        } else {
            throw new IllegalStateException("engine can not be work.");
        }
    }

    /**
     * 停止引擎。
     * 引擎狀態將被設為非運行狀態。
     */
    public void stop() {
        running = false;
    }

    /**
     * 獲取引擎的運行狀態。
     * @return 如果引擎正在運行，返回 true；否則返回 false。
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * 給引擎加注燃料。
     * 燃料量必須在 0-8 的範圍內，超出範圍的值會被自動調整。
     * @param fuelType 填充的燃料類型
     * @param fuelLevel 填充的燃料量
     */
    public void fill(FuelType fuelType, int fuelLevel) {
        if (fuelLevel >= 0 && fuelLevel <= 100) {
            this.fuelLevel = fuelLevel; // 燃料量在範圍內，直接設置
        } else if (fuelLevel > 100) {
            this.fuelLevel = 100; // 超出上限，設置為最大值 100
        } else {
            this.fuelLevel = 0; // 負值情況，設置為 0
        }
        this.fuelType = fuelType; // 設置當前燃料類型
    }

    /**
     * 獲取每批次的成本。
     * @return 每批次的成本
     */
    public double getCostPerBatch() {
        return getCostPerBatch;
    }

    /**
     * 獲取批次大小。
     * @return 每批次的大小
     */
    public int getBatchSize() {
        return batchSize;
    }

	public int getFuelLevel() {
		return this.fuelLevel;
	}
}