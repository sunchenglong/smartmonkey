package common;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import com.android.chimpchat.adb.AdbBackend;
import com.android.chimpchat.adb.AdbChimpDevice;
public class monkeyrunnertest {
    /**
     * @param args
     */
    private static AdbChimpDevice device;
    private static AdbBackend adb;
    public static void main(String[] args) {
    	System.out.println("start!");
       // TODO Auto-generated method stub
        if (adb==null){ 
             adb = new AdbBackend(); 
             device = (AdbChimpDevice) adb.waitForConnection(800,"MB309MG27945");
         } 
        String action = "android.intent.action.MAIN";   
        Collection<String> categories = new ArrayList<String>();   
        categories.add("android.intent.category.LAUNCHER");
        device.startActivity(null, action, null, null, categories,   
        		new HashMap<String, Object>(),"cn.com.fetion/.android.ui.activities.StartActivity", 0);
        device.touch(202,258,com.android.chimpchat.core.TouchPressType.DOWN_AND_UP); 
        device.takeSnapshot().writeToFile("test1.png","png");
        System.out.println("Finished!");
        adb.shutdown();
    }
}