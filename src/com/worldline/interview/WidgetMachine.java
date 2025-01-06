package com.worldline.interview;

/**
 * WidgetMachine 類模擬了一個小工具生產機器，
 * 它可以使用兩種類型的引擎：內燃機（InternalCombustionEngine）和蒸汽機（SteamEngine）。
 * 該類支援引擎啟動、停止、生產小工具以及計算生產成本。
 */
public class WidgetMachine {
    // 引擎對象，用於提供生產動力
    private Engine engine;

    /**
     * WidgetMachine 的建構子，接收一個引擎作為參數。
     * 
     * @param engine 用於生產的引擎（可以是 InternalCombustionEngine 或 SteamEngine）
     */
    public WidgetMachine(Engine engine) {
        super();
        this.engine = engine;
    }

    /**
     * 生產指定數量的小工具，並計算生產成本。
     * 在生產過程中，會啟動引擎、生產並停止引擎。
     * 
     * @param quantity 要生產的小工具數量
     * @return 生產過程的總成本
     */
    public double produceWidgets(int quantity) {
    	//如果生產數量小於fill的數量，丟出燃量不足
    	if(engine.getFuelLevel() < quantity) {
    		throw new IllegalArgumentException("Insufficient fuel level.");
    	}
    	
        // 啟動引擎
        engine.start();
        double cost = 0;

        // 如果引擎成功啟動，開始生產小工具
        if (engine.isRunning()) {
            cost = produce(quantity); // 呼叫內部方法計算生產成本
        }

        // 停止引擎
        engine.stop();

        return cost;
    }

    /**
     * 計算生產小工具的成本。
     * 根據批次大小計算需要的批次數量，並乘以每批次成本得到總成本。
     * 
     * @param quantity 要生產的小工具數量
     * @return 生產過程的總成本
     */
    private double produce(int quantity) {
        int batch = 0; // 當前已生產的數量
        int batchCount = 0; // 所需的批次數量
        double costPerBatch = 0; // 每批次成本

        // 獲取引擎的每批次成本和批次大小
        costPerBatch = engine.getCostPerBatch();
        int batchSize = engine.getBatchSize();

        // 計算需要的批次數量
        while (batch < quantity) {
            batch = batch + batchSize; // 每次增加一個批次的數量
            batchCount++; // 批次數量累加
        }

        // 返回總成本
        return batchCount * costPerBatch;
    }
}
