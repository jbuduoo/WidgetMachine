package test.com.worldline.interview;


import com.worldline.interview.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EngineTest {

    // 用於捕捉和驗證測試中拋出的例外
    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    /**
     * 測試引擎啟動失敗的情況：
     * 當蒸汽引擎加注的燃料量為 0 時，應無法啟動，並拋出 "engine can not be work." 的例外。
     */
    @Test
    public void testStartEngine() {
        Engine engine = new SteamEngine(FuelType.COAL);
        engine.fill(FuelType.COAL, 0); // 燃料量為 0
        exceptions.expect(Exception.class);
        exceptions.expectMessage("engine can not be work.");
        engine.start(); // 嘗試啟動引擎，應該拋出例外
    }

    /**
     * 測試蒸汽引擎使用煤炭作為燃料：
     * 確保蒸汽引擎正確計算生產 3 個 widgets 的成本。
     */
    @Test
    public void TestCOALforSteamEngine() {
        Engine engine = new SteamEngine(FuelType.COAL);
        engine.fill(FuelType.COAL, 30); // 加注 30 單位的煤炭
        WidgetMachine widgetMachine = new WidgetMachine(engine);
        double cost = widgetMachine.produceWidgets(3); // 生產 3 個 widgets
        Assert.assertEquals(cost, 5.65 * 2, 0.001); // 批次大小為 2，需 2 批
    }

    /**
     * 測試蒸汽引擎燃料不足的情況：
     * 當燃料不足無法完成生產時，應拋出 "Insufficient fuel level." 的例外。
     */
    @Test
    public void testThrowException() {
        Engine engine = new SteamEngine(FuelType.WOOD);
        engine.fill(FuelType.WOOD, 2); // 加注 2 單位的木材
        WidgetMachine widgetMachine = new WidgetMachine(engine);
        exceptions.expect(Exception.class);
        exceptions.expectMessage("Insufficient fuel level.");
        double cost = widgetMachine.produceWidgets(6); // 嘗試生產 2 個 widgets，應拋出例外
    }
    
    /**
     * 
     * 木料燃料正常生產
     */
    @Test
    public void testWood() {
        Engine engine = new SteamEngine(FuelType.WOOD);
        engine.fill(FuelType.WOOD, 6); // 加注6 單位的木材
        WidgetMachine widgetMachine = new WidgetMachine(engine);
        double cost = widgetMachine.produceWidgets(6); // 嘗試生產 3 個 widgets
        Assert.assertEquals(cost, 4.35 * 3, 0.001); 
    }

    /**
     * 
     * 當蒸汽引擎填充非木材或煤炭的燃料（如汽油）時，應拋出例外。
     */
    @Test
    public void testForFill() {
        Engine engine = new SteamEngine(FuelType.COAL);
        exceptions.expect(Exception.class);
        exceptions.expectMessage("Invalid fuel type for SteamEngine.");
        engine.fill(FuelType.PETROL, 4); // 嘗試填充汽油
    }
    
    /**
     * 確保引擎在燃料正確時可以啟動，並在停止後不再運行。
     */
    @Test
    public void testStartAndStopEngine() {
        Engine engine = new SteamEngine(FuelType.WOOD);
        engine.fill(FuelType.WOOD, 20); // 加注 20 單位的木材
        engine.start(); // 嘗試啟動引擎
        Assert.assertTrue(engine.isRunning()); // 確保引擎正在運行

        engine.stop(); // 停止引擎
        Assert.assertFalse(engine.isRunning()); // 確保引擎已停止運行
    }
    /**
     * 確保填充燃料時，燃料量不會超過上限（8）。
     */
    @Test
    public void testMaxFuelLevel() {
        Engine engine = new SteamEngine(FuelType.COAL);
        engine.fill(FuelType.COAL, 150); // 嘗試填充超過最大值的燃料
        Assert.assertEquals(100, engine.getFuelLevel()); // 確保燃料量被限制在 100
    }
    /**
     * 確保填充負數燃料量時，燃料量被正確設置為 0。
     */
    @Test
    public void testNegativeFuelLevel() {
        Engine engine = new SteamEngine(FuelType.WOOD);
        engine.fill(FuelType.WOOD, -10); // 嘗試填充負數燃料
        Assert.assertEquals(0, engine.getFuelLevel()); // 確保燃料量被設置為 0
    }
    /**
     * 測試剛好達到批次大小
     */
    @Test
    public void testExactBatchSize() {
        Engine engine = new SteamEngine(FuelType.WOOD);
        engine.fill(FuelType.WOOD, 100); // 加注足夠燃料
        WidgetMachine widgetMachine = new WidgetMachine(engine);
        double cost = widgetMachine.produceWidgets(2); // 生產正好 1 批（批次大小為 2）
        Assert.assertEquals(4.35, cost, 0.001); // 成本應為 1 批的成本
    }
    /**
     * 測試不足一批時的成本計算。
     */
    @Test
    public void testPartialBatch() {
        Engine engine = new SteamEngine(FuelType.WOOD);
        engine.fill(FuelType.WOOD, 100); // 加注足夠燃料
        WidgetMachine widgetMachine = new WidgetMachine(engine);
        double cost = widgetMachine.produceWidgets(3); // 生產 3 個 widgets（需 2 批）
        Assert.assertEquals(4.35 * 2, cost, 0.001); // 成本應為 2 批的成本
    }
}