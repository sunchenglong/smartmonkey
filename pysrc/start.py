from com.android.monkeyrunner import MonkeyRunner,MonkeyDevice 
import sys
def takeshot(filename):
    device = MonkeyRunner.waitForConnection()
    result = device.takeSnapshot()
    result.writeToFile(filename,'png')
if __name__=='__main__':
    takeshot(sys.argv[1])
    
