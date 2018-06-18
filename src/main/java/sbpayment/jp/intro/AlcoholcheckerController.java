package sbpayment.jp.intro;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AlcoholcheckerController {
@Autowired
private JdbcTemplate jdbc;


//topページを返す
@GetMapping("/index")
public String index(Model model) {
	return "index";
}

/*@GetMapping("/check")
public String check(Model model) {
	return "check";

}
*/
@RequestMapping("check")
	public String check(){
	return "check";
}

////フォームに入力された値を受け取り計算してresultに返す(DB未使用)/////
////純アルコール量を計算//////
 @RequestMapping("result")
 public String result(Model model, ModelMap modelmap, @RequestParam("a") int a, @RequestParam("b") int b, @RequestParam("c") int c,
		 @RequestParam("d") int d, @RequestParam("e") int e, @RequestParam("f") int f, @RequestParam("g") int g,
		 @RequestParam("fr") int fr, @RequestParam("dosuu") double dosuu, @RequestParam("weight") int weight) {
	 
	 //データベースから値を取得して計算
	int sum = a * (int)jdbc.queryForList("SELECT * FROM DBAlc").get(0).get("amount");
	int sum1 = b * (int)jdbc.queryForList("SELECT * FROM DBAlc").get(1).get("amount");
	int sum2 = c * (int)jdbc.queryForList("SELECT * FROM DBAlc").get(2).get("amount");
	int sum3 = d * (int)jdbc.queryForList("SELECT * FROM DBAlc").get(3).get("amount");
	int sum4 = e * (int)jdbc.queryForList("SELECT * FROM DBAlc").get(4).get("amount");
	int sum5 = f * (int)jdbc.queryForList("SELECT * FROM DBAlc").get(5).get("amount"); 
	int sum6 = g * fr;
	int gou = sum + sum1 + sum2 + sum3 + sum4 + sum5 + sum6;
	
	double Al = (sum * (int)jdbc.queryForList("SELECT * FROM DBAlc").get(0).get("alcohol") * 0.01) * 0.8;
	double Al1 = (sum1 * (int)jdbc.queryForList("SELECT * FROM DBAlc").get(1).get("alcohol") * 0.01) * 0.8;
	double Al2 = (sum2 * (int)jdbc.queryForList("SELECT * FROM DBAlc").get(2).get("alcohol") * 0.01) * 0.8; 
	double Al3 = (sum3 * (int)jdbc.queryForList("SELECT * FROM DBAlc").get(3).get("alcohol") * 0.01) * 0.8;
	double Al4 = (sum4 * (int)jdbc.queryForList("SELECT * FROM DBAlc").get(4).get("alcohol") * 0.01) * 0.8;
	double Al5 = (sum5 * (int)jdbc.queryForList("SELECT * FROM DBAlc").get(5).get("alcohol") * 0.01) * 0.8;
	double Al6 = (sum6 * dosuu * 0.01) * 0.8;
	
	modelmap.addAttribute("a",a);
	modelmap.addAttribute("sum",sum);
	modelmap.addAttribute("b",b);
	modelmap.addAttribute("sum1",sum1);
	modelmap.addAttribute("c",c);
	modelmap.addAttribute("sum2",sum2);
	modelmap.addAttribute("d",d);
	modelmap.addAttribute("sum3",sum3);
	modelmap.addAttribute("e",e);
	modelmap.addAttribute("sum4",sum4);
	modelmap.addAttribute("f",f);
	modelmap.addAttribute("sum5",sum5);
	modelmap.addAttribute("g",g);
	modelmap.addAttribute("fr",fr);
	modelmap.addAttribute("sum6",sum6);
	modelmap.addAttribute("dosuu",dosuu);
	modelmap.addAttribute("weight",weight);
	
	/////飲んだ量の合計
	modelmap.addAttribute("gou",gou);
	
	/////純アルコール量の計算
	modelmap.addAttribute("Al",Al);
	modelmap.addAttribute("Al1",Al1);
	modelmap.addAttribute("Al2",Al2);
	modelmap.addAttribute("Al3",Al3);
	modelmap.addAttribute("Al4",Al4);
	modelmap.addAttribute("Al5",Al5);
	modelmap.addAttribute("Al6",Al6);
	
	/////純アルコール量の合計
	double gouAl = Al + Al1 + Al2 + Al3 + Al4 + Al5 + Al6;
	modelmap.addAttribute("gouAl",gouAl);
	
	////アルコール分解までの時間
	double times = gouAl / (weight * 0.1);
	modelmap.addAttribute("times",String.format("%.1f", times));
	
	return "result";
 }

 
 
//結果を表示する
/*@GetMapping("/result")
public String result() {
	return "result";
}

//@PostMapping("/post")
//public String Post(String name, int age,RedirectAttributes attr){
   // attr.addFlashAttribute("number", number);
    
    
    /*jdbc.update("insert into person (age,name) values(?,?);", age,name);
    attr.addFlashAttribute("people",jdbc.queryForList("SELECT * FROM person"));
    //Map<String, Object> person = jdbc.queryForList("SELECT * FROM person where name = ?", name).get(0);
    return "redirect:/sample";*/
}


