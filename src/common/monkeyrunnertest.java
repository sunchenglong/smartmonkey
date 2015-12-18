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
       // TODO Auto-generated method stub
        if (adb==null){ 
             adb = new AdbBackend(); 
              //参数分别为自己定义的等待连接时间和设备id 
              //这里需要注意一下adb的类型
             device = (AdbChimpDevice) adb.waitForConnection(8000,"1af74e33");
         } 
        //添加启动权限
             // String action = "android.intent.action.MAIN";   
        //    Collection<String> categories = new ArrayList<String>();   
      //    categories.add("android.intent.category.LAUNCHER");
      //      启动要测试的主界面
      //      device.startActivity(null, action, null, null, categories,   
      //      new HashMap<String, Object>(),"cn.com.fetion/.android.ui.activities.StartActivity", 0); 
      //           点击某一个坐标
      //touch方法略有变化           
      device.touch(202,258,com.android.chimpchat.core.TouchPressType.DOWN_AND_UP); 
      device.takeSnapshot().writeToFile("test1.png","png");
    }
    }