package com.tigerjoys.data.dragon.appclient.mock.fingerprint;

import com.tigerjoys.data.dragon.appclient.mock.FingerPrintMocker;
import com.tokyohot.shibuya.finger.origin.app.AppData;
import com.tokyohot.shibuya.finger.origin.app.AppFinger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppFingerMocker extends FingerPrintMocker {

    /**
     * 模拟对象数据
     */
    @Override
    public Serializable mock() {
        List<AppData> appDataList = new ArrayList<>();
        AppData appData1 = new AppData();
        appData1.setPackageName("testPackageName");
        appData1.setName("testName");
        appData1.setSys(true);
        appData1.setCard(true);
        appDataList.add(appData1);
        AppData appData2 = new AppData();
        appData2.setPackageName("testPackageName2");
        appData2.setName("testName2");
        appData2.setSys(false);
        appData2.setCard(false);
        appDataList.add(appData2);

        AppFinger appFinger = new AppFinger();
        appFinger.setConversationID(getConversation().getID());
        appFinger.setStart(1);
        appFinger.setEnd(1);
        appFinger.setError(1);
        appFinger.setDataList(appDataList);
        appFinger.setCount(2);

        return appFinger;
    }

}
