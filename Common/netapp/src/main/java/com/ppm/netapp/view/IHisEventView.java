package com.ppm.netapp.view;

import com.ppm.netapp.model.HisEvent;
import com.ppm.ppcomon.base.view.intf.IBaseView;

public interface IHisEventView extends IBaseView {

    void onGetHistoryEvent(HisEvent hisEvent);
}
