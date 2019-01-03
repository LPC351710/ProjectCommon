package com.ppm.netapp.view;

import com.ppm.netapp.model.HisDetails;
import com.ppm.ppcomon.base.view.intf.IBaseView;

public interface IDetailsView extends IBaseView {

    void onGetHisDetails(HisDetails hisDetails);
}
