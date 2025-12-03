package com.example.demo.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FoodDao {

	// foodテーブルから、カテゴリ名(category_name) を条件に、該当する全ての食品名(food_name) を取得する	
	@Select("""
			SELECT
			  food_name
			FROM
			  food
			WHERE
			  category_name = #{searchWord}; 
			""") 
	//ここまでがアノテーション部分(下のメソッドに付与されている)
	//selsctFoodNameメソッド実行時に引数に指定された文字列がSQL文に組み込まれる
	public ArrayList<String> selectFoodName(String searchWord); //selectFoodNameメソッド定義部分

}
/* selectFoodNameメソッドが呼び出される
 * →引数として受け取った文字列が検索条件となり、foodテーブルに対してSELECT文が実行される
 * →条件に合致するcategory_nameを持つデータ全てのfood_nameがString型のArrayListとしてメソッド呼び出し元に返却される */
