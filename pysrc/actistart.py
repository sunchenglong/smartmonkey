from com.android.monkeyrunner import MonkeyRunner,MonkeyDevice 
import sys
def takeshot(activity):
    device = MonkeyRunner.waitForConnection()
    device.startActivity(component="com.example.android.apis/com.example.android.apis.ApiDemos")
    print activity+" have started"
if __name__=='__main__':
    takeshot(sys.argv[1])
    
