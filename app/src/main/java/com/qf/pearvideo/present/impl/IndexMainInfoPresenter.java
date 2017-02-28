package com.qf.pearvideo.present.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.qf.pearvideo.bean.Cont;
import com.qf.pearvideo.bean.Node;
import com.qf.pearvideo.bean.NodeInfo;
import com.qf.pearvideo.callback.PearStringCallBack;
import com.qf.pearvideo.dao.impl.PearDao;
import com.qf.pearvideo.fragment.InterFaces.IIndexMainFragment;
import com.qf.pearvideo.present.IIndexMainInfoPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */

public class IndexMainInfoPresenter implements IIndexMainInfoPresenter {

    private Context context;
    private IIndexMainFragment mIIndexMainFragment;
    private SharedPreferences sp;
    private PearDao pearDao = new PearDao();

    //返回的数据
    private PearStringCallBack callBack = new PearStringCallBack() {
        @Override
        public void doResult(String resultStr, int tag) {
            if (tag == 1){
                //返回的Json字符串，进行解析
                try {
                    JSONObject jsonObject = new JSONObject(resultStr);
                    int resultCode = jsonObject.getInt("resultCode");
                    if (resultCode != 1){
                        //数据出错
                    }else {
                        //解析JSON
                        JSONArray array = jsonObject.getJSONArray("dataList");
                        int size = array.length();
                        List<Node> nodeList = new ArrayList<>();
                        for (int i = 0; i < size; i++) {
                            JSONObject nodeJsonObj = array.getJSONObject(i);
                            if (nodeJsonObj.getString("nodeName").equals("直播中")){
                                continue;//跳过直播的Json
                            }
                            Node node = new Node();
                            node.setNodeType(nodeJsonObj.getInt("nodeType"));
                            node.setNodeName(nodeJsonObj.getString("nodeName"));
                            List<Cont> contList = new ArrayList<>();

                            JSONArray contArr = nodeJsonObj.getJSONArray("contList");
                            int contSize = contArr.length();
                            for (int j = 0; j < contSize; j++){
                                JSONObject contJsonObj = contArr.getJSONObject(j);
                                Cont cont = new Cont();
                                cont.setContId(contJsonObj.getInt("contId"));
                                cont.setContName(contJsonObj.getString("name"));
                                cont.setPic(contJsonObj.getString("pic"));
                                cont.setDuration(contJsonObj.getString("duration"));
                                cont.setVideoPath(contJsonObj.getString("coverVideo"));

                                String labelDesc = contJsonObj.getString("cornerLabelDesc");
                                if (labelDesc != null && !labelDesc.equals("")){
                                    cont.setLabel(labelDesc);
                                }

                                NodeInfo nodeInfo = new NodeInfo();
                                JSONObject nodeInfoObj = contJsonObj.getJSONObject("nodeInfo");
                                nodeInfo.setNodeId(nodeInfoObj.getInt("nodeId"));
                                nodeInfo.setNodeName(nodeInfoObj.getString("name"));
                                nodeInfo.setNodeLogoImgPath(nodeInfoObj.getString("logoImg"));
                                //添加到cont里面
                                cont.setNodeInfo(nodeInfo);
                                //将cont添加到集合中
                                contList.add(cont);
                            }
                            node.setContList(contList);
                            //添加到node集合中
                            nodeList.add(node);
                        }
                        //将所得的nodeList返回
                        mIIndexMainFragment.getNodeList(nodeList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public IndexMainInfoPresenter(Context context, IIndexMainFragment mIIndexMainFragment) {
        this.context = context;
        this.mIIndexMainFragment = mIIndexMainFragment;
    }

    @Override
    public void getMainInfo(String url) {
        sp = context.getSharedPreferences("PearVideoCookie", Context.MODE_PRIVATE);
        String cookie = sp.getString("cookie","ERROR");
        pearDao.getIndexMainInfo(url, cookie, callBack);
    }
}
