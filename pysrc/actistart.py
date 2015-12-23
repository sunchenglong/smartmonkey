from com.android.monkeyrunner import MonkeyRunner,MonkeyDevice 
import sys
def startactivity(activity):
    device = MonkeyRunner.waitForConnection()
#    device.startActivity(component="com.example.android.apis/com.example.android.apis.ApiDemos")
#    device.startActivity(component="com.android.providers.calendar")
    device.startActivity(component="com.example.android.snake/com.example.android.snake.Snake")
    print activity+" have started"
if __name__=='__main__':
    startactivity(sys.argv[1])
    
