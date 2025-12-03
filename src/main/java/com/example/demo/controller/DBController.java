package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.FoodDao;


@Controller
public class DBController {

	// foodDao という変数名で FoodDao インターフェースを利用するための宣言
	@Autowired
	private FoodDao foodDao;
	
	// 検索画面の表示
	//【Ｑ１】URL「http://localhost:8080/db/search」とひもづけする
	@GetMapping("/db/search")
	public ModelAndView showSearchForm(ModelAndView mav) {
		//【Ｑ２】次に表示させるHTMLファイル名を指定する文
		mav.setViewName("search");
		return mav;
	}
	
	// 検索結果の表示
	//【Ｑ３】URL「http://localhost:8080/db/result」とひもづけする
	@GetMapping("/db/result")
	//【Ｑ４】リクエストとして送られてきた値 searchWord を引数「String searchWord」に格納する
	//@RequestParamアノテーションにより、ユーザーが選択した検索ワードがsearchWordに格納
	public ModelAndView search(@RequestParam("searchWord") String searchWord, ModelAndView mav) {
		// searchメソッドの処理内容
		ArrayList<String> foodNameList = foodDao.selectFoodName(searchWord);
		//(右辺)@Autowired宣言部分によってFoodDaoインターフェースをfoodDaoという変数名で使える
		//(右辺)selectFoodNameメソッド：引数として受け取った文字列を条件にfoodテーブルを検索し、該当する全ての食品名をArrayListの形で返すメソッド
		//(右辺)selectFoodNameメソッドの引数はsearchWord
		//(左辺)データ型がArrayList<String>の、foodNameListという新たな変数を宣言→右辺の内容が代入される
		mav.addObject("foodNameList", foodNameList);
		mav.addObject("searchWord", searchWord);
		//変数mavのaddObjectメソッドでレスポンス内容を二つ指定
		//・foodNameList：データベースの検索結果(ArrayList型)
		//・searchWord：ユーザーが選択した検索ワード
		
		mav.setViewName("result");
		return mav;
		//変数mavのsetViewNameメソッドで次の表示画面をresult.htmlと指定
		//mavをメソッドの戻り値に指定することでレスポンス内容/次の表示画面が確定
	}

}
