package testNG.Listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class LoginListeners implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        String methName = result.getMethod().getMethodName();
        System.out.println(methName+" test Success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        String methName = result.getMethod().getMethodName();
//        Throwable error = result.getThrowable();
//        System.out.println(methName + " test Failed under the error: "+error);
        System.out.println("❌ Test Failed:");
        System.out.println("   Test Case: " + result.getMethod().getMethodName());
        System.out.println("   Error : " + result.getThrowable().getMessage());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methName = result.getMethod().getMethodName();
        System.out.println(methName+" test Skipped");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        String methName = result.getMethod().getMethodName();
        System.out.println(methName+" test Failed With Timeout");
    }

//    @Override
//    public void onStart(ITestContext context) {
//        System.out.println("Login test started!");
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        System.out.println("Login test Finish!");
//    }
}
