package com.example.user.application.health;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 15. 8. 13.
 */
public class HealthParser {
    final String json = "[{\"clCdNm\":\"상급종합\",\"telno\":\"051-895-5001~8\",\"XPos\":\"129.0208609\",\"YPos\":\"35.1455173\",\"yadmNm\":\"(학교법인)인제대학교부산백병원\",\"addr\":\"부산광역시 부산진구 복지로 75 (개금동)\"}," +
            "{\"clCdNm\":\"상급종합\",\"telno\":\"032-1544-9004\",\"XPos\":\"126.7248987\",\"YPos\":\"37.4848309\",\"yadmNm\":\"가톨릭대학교인천성모병원\",\"addr\":\"인천광역시 부평구 동수로 56 (부평동)\"}," +
            "{\"clCdNm\":\"상급종합\",\"telno\":\"02-2001-2001\",\"XPos\":\"126.96775\",\"YPos\":\"37.5684083\",\"yadmNm\":\"강북삼성병원\",\"addr\":\"서울특별시 종로구 새문안로 29 (평동)\"}," +
            "{\"clCdNm\":\"상급종합\",\"telno\":\"02-1588-1533\",\"XPos\":\"127.0718276\",\"YPos\":\"37.5403764\",\"yadmNm\":\"건국대학교병원\",\"addr\":\"서울특별시 광진구 능동로 120-1 (화양동)\"}," +
            "{\"clCdNm\":\"상급종합\",\"telno\":\"053-420-5114\",\"XPos\":\"128.604125\",\"YPos\":\"35.866774\",\"yadmNm\":\"경북대학교병원\",\"addr\":\"대구광역시 중구 동덕로 130 (삼덕동2가,경북대학교병원)\"}," +
            "{\"clCdNm\":\"상급종합\",\"telno\":\"055-750-8000\",\"XPos\":\"128.0956717\",\"YPos\":\"35.1763252\",\"yadmNm\":\"경상대학교병원\",\"addr\":\"경상남도 진주시 강남로 79 (칠암동,경상대학교병원)\"}," +
            "{\"clCdNm\":\"상급종합\",\"telno\":\"02-958-8114\",\"XPos\":\"127.051852\",\"YPos\":\"37.5941195\",\"yadmNm\":\"경희대학교병원\",\"addr\":\"서울특별시 동대문구 경희대로 23 (회기동)\"}," +
            "{\"clCdNm\":\"상급종합\",\"telno\":\"053-250-7114\",\"XPos\":\"128.5836\",\"YPos\":\"35.869999\",\"yadmNm\":\"계명대학교동산병원\",\"addr\":\"대구광역시 중구 달성로 56 (동산동,계명대학교동산의료원)\"}," +
            "{\"clCdNm\":\"상급종합\",\"telno\":\"02-2626-1114\",\"XPos\":\"126.8848701\",\"YPos\":\"37.492052\",\"yadmNm\":\"고려대의과대학부속구로병원\",\"addr\":\"서울특별시 구로구 구로동로 148 (구로동)\"}," +
            "{\"clCdNm\":\"상급종합\",\"telno\":\"031-412-5114\",\"XPos\":\"126.8249033\",\"YPos\":\"37.3185144\",\"yadmNm\":\"고려대학교의과대학부속안산병원\",\"addr\":\"경기도 안산시 단원구 적금로 123 (고잔동)\"}]";

    public ArrayList<Health> jsonParser() {
        ArrayList<Health> list = new ArrayList<Health>();
        try {
            //JSON String으로 부터 JSONArray 생성. [](대괄호)
            JSONArray jArr = new JSONArray(json);
            for (int i = 0; i < jArr.length(); i++) {
                //JSONArray에서 i번째 해당하는 JSONObject를 추출.
                JSONObject jObj = jArr.getJSONObject(i);
                Health hospital = new Health();
                //각 이름("id"/"tel")에 해당하는 값을 추출.
                hospital.setName(jObj.getString("yadmNm"));
                hospital.setAddr(jObj.getString("addr"));
                hospital.setClcdnm(jObj.getString("clCdNm"));
                hospital.setTelno(jObj.getString("telno"));
                hospital.setxPos(Double.valueOf(jObj.getString("XPos")));
                hospital.setyPos(Double.valueOf(jObj.getString("YPos")));
                list.add(hospital);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}