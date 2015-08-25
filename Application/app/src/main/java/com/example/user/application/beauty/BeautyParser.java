package com.example.user.application.beauty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 13.
 */
public class BeautyParser {
    final String json = "[{\"clCdNm\":\"미용업(일반)\",\"telno\":\"051-342-8744\",\"XPos\":\"129.0131323\",\"YPos\":\"35.257394\",\"yadmNm\":\"홍성숙헤어갤러리\",\"addr\":\"부산광역시 북구 금곡동 811번지 3호  주공7단지 상가 101호\"}," +
            "{\"clCdNm\":\"미용업(일반)\",\"telno\":\"051-362-7014\",\"XPos\":\"128.9966605\",\"YPos\":\"35.2013329\",\"yadmNm\":\"김미란헤어샾\",\"addr\":\"부산광역시 북구 구포동 927번지 1호\"}," +
            "{\"clCdNm\":\"미용업(일반)\",\"telno\":\"051-365-1706\",\"XPos\":\"129.0087195\",\"YPos\":\"35.2253368\",\"yadmNm\":\"이승희헤어클리닉\",\"addr\":\"부산광역시 북구 화명동 958번지 3호  거림상가 201-1\"}," +
            "{\"clCdNm\":\"미용업(일반)\",\"telno\":\"051-331-5416\",\"XPos\":\"129.0117904\",\"YPos\":\"35.1968481\",\"yadmNm\":\"오월의신부\",\"addr\":\"부산광역시 북구 구포동 1237번지 42호\"}," +
            "{\"clCdNm\":\"미용업(일반)\",\"telno\":\"정보없음\",\"XPos\":\"129.0145874\",\"YPos\":\"35.1978213\",\"yadmNm\":\"비달싹뚝\",\"addr\":\"부산광역시 북구 구포동 1241번지 58호\"}," +
            "{\"clCdNm\":\"미용업(일반)\",\"telno\":\"051-343-6060\",\"XPos\":\"129.0084263\",\"YPos\":\"35.1983574\",\"yadmNm\":\"민수연헤어\",\"addr\":\"부산광역시 북구 구포동 1228번지 8호\"}," +
            "{\"clCdNm\":\"미용업(일반)\",\"telno\":\"051-341-2260\",\"XPos\":\"129.0067787\",\"YPos\":\"35.1993305\",\"yadmNm\":\"미들수헤어\",\"addr\":\"부산광역시 북구 구포동 1222번지 24호\"}," +
            "{\"clCdNm\":\"미용업(일반)\",\"telno\":\"051-338-7772\",\"XPos\":\"129.0165422\",\"YPos\":\"35.2556821\",\"yadmNm\":\"이공삼(203)헤어클럽\",\"addr\":\"부산광역시 북구 금곡동 60번지 1호\"}," +
            "{\"clCdNm\":\"미용업(일반)\",\"telno\":\"정보없음\",\"XPos\":\"128.9949636\",\"YPos\":\"35.1964158\",\"yadmNm\":\"조수희미용실\",\"addr\":\"부산광역시 북구 구포동 1090번지\"}," +
            "{\"clCdNm\":\"미용업(일반)\",\"telno\":\"051-336-2427\",\"XPos\":\"129.0144856\",\"YPos\":\"35.2370402\",\"yadmNm\":\"금비헤어샵\",\"addr\":\"부산광역시 북구 화명동 1412번지 2호\"}]";

    public ArrayList<Beauty> jsonParser() {
        ArrayList<Beauty> list = new ArrayList<Beauty>();
        try {
            //JSON String으로 부터 JSONArray 생성. [](대괄호)
            JSONArray jArr = new JSONArray(json);
            for (int i = 0; i < jArr.length(); i++) {
                //JSONArray에서 i번째 해당하는 JSONObject를 추출.
                JSONObject jObj = jArr.getJSONObject(i);
                Beauty beauty = new Beauty();
                //각 이름("id"/"tel")에 해당하는 값을 추출.
                beauty.setName(jObj.getString("yadmNm"));
                beauty.setAddr(jObj.getString("addr"));
                beauty.setClcdnm(jObj.getString("clCdNm"));
                beauty.setTelno(jObj.getString("telno"));
                beauty.setxPos(Double.valueOf(jObj.getString("XPos")));
                beauty.setyPos(Double.valueOf(jObj.getString("YPos")));
                list.add(beauty);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}