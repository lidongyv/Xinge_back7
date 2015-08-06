package com.example.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import com.example.xinge.DataForm;
import android.R.integer;
import android.content.Context;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.app.Application;  
import com.example.adapter.TextAdapter;
import com.example.xinge.DataForm;
import com.example.xinge.R;

public class ViewMiddle extends LinearLayout implements ViewBaseAction {
	
	private ListView regionListView;
	private ListView plateListView;
	private ArrayList<String> groups = new ArrayList<String>();
	private LinkedList<String> childrenItem = new LinkedList<String>();
	private SparseArray<LinkedList<String>> children = new SparseArray<LinkedList<String>>();
	private TextAdapter plateListViewAdapter;
	private TextAdapter earaListViewAdapter;
	private OnSelectListener mOnSelectListener;
	private int tEaraPosition = 0;
	private int tBlockPosition = 0;
	private String showString = "选择地区";
	private DataForm dataForm;
	public String []place={"","",""};
	public ViewMiddle(Context context) {
		super(context);
		init(context);
	}

	public ViewMiddle(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void updateShowText(String showArea, String showBlock) {
		if (showArea == null || showBlock == null) {
			return;
		}
		for (int i = 0; i < groups.size(); i++) {
			if (groups.get(i).equals(showArea)) {
				earaListViewAdapter.setSelectedPosition(i);
				childrenItem.clear();
				if (i < children.size()) {
					childrenItem.addAll(children.get(i));
				}
				tEaraPosition = i;
				break;
			}
		}
		for (int j = 0; j < childrenItem.size(); j++) {
			if (childrenItem.get(j).replace("不限", "").equals(showBlock.trim())) {
				plateListViewAdapter.setSelectedPosition(j);
				tBlockPosition = j;
				break;
			}
		}
		setDefaultSelect();
	}

	private void init(Context context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_region, this, true);
		regionListView = (ListView) findViewById(R.id.listView);
		plateListView = (ListView) findViewById(R.id.listView2);
		setBackgroundDrawable(getResources().getDrawable(
				R.drawable.choosearea_bg_left));

		/*for(int i=0;i<10;i++){
			groups.add(i+"行");
			LinkedList<String> tItem = new LinkedList<String>();
			for(int j=0;j<15;j++){
				
				tItem.add(i+"行"+j+"列");
				
			}
			children.put(i, tItem);
		}*/
		int i=0;
		int j=0;
		groups.add("北京市");
		LinkedList<String> tItem = new LinkedList<String>();
		tItem.add("北京市");
		children.put(i, tItem);
		i++;
		groups.add("天津市");
		LinkedList<String> tItem2 = new LinkedList<String>();
		tItem2.add("天津市");
		children.put(i, tItem2);
		i++;
		groups.add("河北省");
		LinkedList<String> tItem3 = new LinkedList<String>();
		tItem3.add("石家庄市");
		tItem3.add("唐山市");
		tItem3.add("秦皇岛市");
		tItem3.add("邯郸市");
		tItem3.add("邢台市");
		tItem3.add("保定市");
		tItem3.add("张家口市");
		tItem3.add("承德市");
		tItem3.add("沧州市");
		tItem3.add("廊坊市");
		tItem3.add("衡水市");
		children.put(i, tItem3);
		i++;
		groups.add("山西省");
		LinkedList<String> tItem4 = new LinkedList<String>();
		tItem4.add("太原市");
		tItem4.add("大同市");
		tItem4.add("阳泉市");
		tItem4.add("长治市");
		tItem4.add("晋城市");
		tItem4.add("朔州市");
		tItem4.add("晋中市");
		tItem4.add("运城市");
		tItem4.add("忻州市");
		tItem4.add("临汾市");
		tItem4.add("吕梁市");
		children.put(i, tItem4);
		i++;
		groups.add("内蒙古");
		LinkedList<String> tItem5 = new LinkedList<String>();
		tItem5.add("呼和浩特市");
		tItem5.add("包头市");
		tItem5.add("乌海市");
		tItem5.add("赤峰市");
		tItem5.add("通辽市");
		tItem5.add("鄂尔多斯市");
		tItem5.add("呼伦贝尔市");
		tItem5.add("巴彦淖尔市");
		tItem5.add("乌兰察布市");
		tItem5.add("临汾市");
		tItem5.add("锡林郭勒盟");
		tItem5.add("阿拉善盟");
		children.put(i, tItem5);
		i++;
		groups.add("辽宁省");
		LinkedList<String> tItem6 = new LinkedList<String>();
		tItem6.add("沈阳市");
		tItem6.add("大连市");
		tItem6.add("鞍山市");
		tItem6.add("抚顺市");
		tItem6.add("本溪市");
		tItem6.add("丹东市");
		tItem6.add("锦州市");
		tItem6.add("营口市");
		tItem6.add("阜新市");
		tItem6.add("辽阳市");
		tItem6.add("盘锦市");
		tItem6.add("铁岭市");
		tItem6.add("朝阳市");
		tItem6.add("葫芦岛市");
		children.put(i, tItem6);
		i++;
		groups.add("吉林省");
		LinkedList<String> tItem7 = new LinkedList<String>();
		tItem7.add("长春市");
		tItem7.add("吉林市");
		tItem7.add("四平市");
		tItem7.add("辽源市");
		tItem7.add("通化市");
		tItem7.add("白山市");
		tItem7.add("松原市");
		tItem7.add("白城市");
		tItem7.add("延边朝鲜族自治州");
		children.put(i, tItem7);
		i++;
		groups.add("黑龙江");
		LinkedList<String> tItem8 = new LinkedList<String>();
		tItem8.add("哈尔滨市");
		tItem8.add("齐齐哈尔市");
		tItem8.add("鸡西市");
		tItem8.add("鹤岗市");
		tItem8.add("双鸭山市");
		tItem8.add("大庆市");
		tItem8.add("伊春市");
		tItem8.add("营口市");
		tItem8.add("七台河市");
		tItem8.add("牡丹江市");
		tItem8.add("黑河市");
		tItem8.add("绥化市");
		tItem8.add("大兴安岭地区");
		children.put(i, tItem8);
		i++;
		groups.add("上海市");
		LinkedList<String> tItem9 = new LinkedList<String>();
		tItem9.add("上海市");
		children.put(i, tItem9);
		i++;
		groups.add("江苏省");
		LinkedList<String> tItem10 = new LinkedList<String>();
		tItem10.add("南京市");
		tItem10.add("无锡市");
		tItem10.add("徐州市");
		tItem10.add("常州市");
		tItem10.add("南通市");
		tItem10.add("连云港市");
		tItem10.add("淮安市");
		tItem10.add("盐城市");
		tItem10.add("扬州市");
		tItem10.add("镇江市");
		tItem10.add("泰州市");
		tItem10.add("宿迁市");
		children.put(i, tItem10);
		i++;
		groups.add("浙江省");
		LinkedList<String> tItem11 = new LinkedList<String>();
		tItem11.add("杭州市");
		tItem11.add("宁波市");
		tItem11.add("温州市");
		tItem11.add("嘉兴市");
		tItem11.add("湖州市");
		tItem11.add("绍兴市");
		tItem11.add("金华市");
		tItem11.add("衢州市");
		tItem11.add("舟山市");
		tItem11.add("台州市");
		tItem11.add("丽水市");
		children.put(i, tItem11);
		i++;
		groups.add("安徽省");
		LinkedList<String> tItem12 = new LinkedList<String>();
		tItem12.add("合肥市");
		tItem12.add("芜湖市");
		tItem12.add("蚌埠市");
		tItem12.add("淮南市");
		tItem12.add("马鞍山市");
		tItem12.add("淮北市");
		tItem12.add("铜陵市");
		tItem12.add("安庆市");
		tItem12.add("黄山市");
		tItem12.add("滁州市");
		tItem12.add("阜阳市");
		tItem12.add("宿州市");
		tItem12.add("巢湖市");
		tItem12.add("六安市");
		tItem12.add("毫州市");
		tItem12.add("池州市");
		tItem12.add("宣城市");
		children.put(i, tItem12);
		i++;
		groups.add("福建省");
		LinkedList<String> tItem13 = new LinkedList<String>();
		tItem13.add("福州市");
		tItem13.add("厦门市");
		tItem13.add("莆田市");
		tItem13.add("三明市");
		tItem13.add("泉州市");
		tItem13.add("漳州市");
		tItem13.add("南平市");
		tItem13.add("龙岩市");
		tItem13.add("宁德市");
		children.put(i, tItem13);
		i++;
		groups.add("江西省");
		LinkedList<String> tItem14 = new LinkedList<String>();
		tItem14.add("南昌市");
		tItem14.add("景德镇市");
		tItem14.add("萍乡市");
		tItem14.add("九江市");
		tItem14.add("新余市");
		tItem14.add("鹰潭市");
		tItem14.add("赣州市");
		tItem14.add("吉安市");
		tItem14.add("宜春市");
		tItem14.add("抚州市");
		tItem14.add("上饶市");
		children.put(i, tItem14);
		i++;
		groups.add("山东省");
		LinkedList<String> tItem15 = new LinkedList<String>();
		tItem15.add("济南市");
		tItem15.add("青岛市");
		tItem15.add("淄博市");
		tItem15.add("枣庄市");
		tItem15.add("东营市");
		tItem15.add("烟台市");
		tItem15.add("潍坊市");
		tItem15.add("济宁市");
		tItem15.add("泰安市");
		tItem15.add("威海市");
		tItem15.add("日照市");
		tItem15.add("莱芜市");
		tItem15.add("临沂市");
		tItem15.add("德州市");
		tItem15.add("聊城市");
		tItem15.add("滨州市");
		tItem15.add("菏泽市");
		children.put(i, tItem15);
		i++;
		groups.add("河南省");
		LinkedList<String> tItem16 = new LinkedList<String>();
		tItem16.add("郑州市");
		tItem16.add("开封市");
		tItem16.add("洛阳市");
		tItem16.add("平顶山市");
		tItem16.add("安阳市");
		tItem16.add("鹤壁市");
		tItem16.add("新乡市");
		tItem16.add("焦作市");
		tItem16.add("濮阳市");
		tItem16.add("许昌市");
		tItem16.add("漯河市");
		tItem16.add("三门峡市");
		tItem16.add("南阳市");
		tItem16.add("商丘市");
		tItem16.add("信阳市");
		tItem16.add("周口市");
		tItem16.add("驻马店市");
		children.put(i, tItem16);
		i++;
		groups.add("湖北省");
		LinkedList<String> tItem17 = new LinkedList<String>();
		tItem17.add("武汉市");
		tItem17.add("黄石市");
		tItem17.add("十堰市");
		tItem17.add("宜昌市");
		tItem17.add("襄樊市");
		tItem17.add("鄂州市");
		tItem17.add("荆门市");
		tItem17.add("孝感市");
		tItem17.add("荆州市");
		tItem17.add("黄冈市");
		tItem17.add("咸宁市");
		tItem17.add("随州市");
		tItem17.add("恩施土家族苗族自治州");
		children.put(i, tItem17);
		i++;
		groups.add("湖南省");
		LinkedList<String> tItem18 = new LinkedList<String>();
		tItem18.add("长沙市");
		tItem18.add("株洲市");
		tItem18.add("湘潭市");
		tItem18.add("衡阳市");
		tItem18.add("邵阳市");
		tItem18.add("岳阳市");
		tItem18.add("常德市");
		tItem18.add("张家界市");
		tItem18.add("益阳市");
		tItem18.add("郴州市");
		tItem18.add("永州市");
		tItem18.add("怀化市");
		tItem18.add("娄底市");
		tItem18.add("湘西土家族苗族自治州");
		children.put(i, tItem18);
		i++;
		groups.add("广州省");
		LinkedList<String> tItem19 = new LinkedList<String>();
		tItem19.add("广州市");
		tItem19.add("韶关市");
		tItem19.add("深圳市");
		tItem19.add("珠海市");
		tItem19.add("汕头市");
		tItem19.add("佛山市");
		tItem19.add("江门市");
		tItem19.add("湛江市");
		tItem19.add("茂名市");
		tItem19.add("肇庆市");
		tItem19.add("惠州市");
		tItem19.add("梅州市");
		tItem19.add("汕尾市");
		tItem19.add("河源市");
		tItem19.add("阳江市");
		tItem19.add("清远市");
		tItem19.add("东莞市");
		tItem19.add("中山市");
		children.put(i, tItem19);
		i++;
		groups.add("广西");
		LinkedList<String> tItem20 = new LinkedList<String>();
		tItem20.add("南宁市");
		tItem20.add("柳州市");
		tItem20.add("桂州市");
		tItem20.add("梧州市");
		tItem20.add("北海市");
		tItem20.add("防城港市");
		tItem20.add("钦州市");
		tItem20.add("贵港市");
		tItem20.add("玉林市");
		tItem20.add("百色市");
		tItem20.add("贺州市");
		tItem20.add("河池市");
		tItem20.add("来宾市");
		tItem20.add("崇左市");
		children.put(i, tItem20);
		i++;
		groups.add("海南省");
		LinkedList<String> tItem21 = new LinkedList<String>();
		tItem21.add("海口市");
		tItem21.add("三亚市");
		children.put(i, tItem21);
		i++;
		groups.add("重庆省");
		LinkedList<String> tItem22 = new LinkedList<String>();
		tItem22.add("重庆市");
		children.put(i, tItem22);
		i++;
		groups.add("四川省");
		LinkedList<String> tItem23 = new LinkedList<String>();
		tItem23.add("成都市");
		tItem23.add("自贡市");
		tItem23.add("攀枝花市");
		tItem23.add("泸州市");
		tItem23.add("德阳市");
		tItem23.add("绵阳市");
		tItem23.add("广元市");
		tItem23.add("遂宁市");
		tItem23.add("内江市");
		tItem23.add("乐山市");
		tItem23.add("南充市");
		tItem23.add("眉山市");
		tItem23.add("宜宾市");
		tItem23.add("广安市");
		tItem23.add("达州市");
		tItem23.add("雅安市");
		tItem23.add("巴中市");
		tItem23.add("资阳市");
		tItem23.add("阿坝藏族羌族自治州");
		tItem23.add("甘孜藏族自治州");
		tItem23.add("凉山彝族自治州");
		children.put(i, tItem23);
		i++;
		groups.add("贵州省");
		LinkedList<String> tItem24 = new LinkedList<String>();
		tItem24.add("贵阳市");
		tItem24.add("六盘水市");
		tItem24.add("遵义市");
		tItem24.add("安顺市");
		tItem24.add("铜仁市");
		tItem24.add("黔西南布依族苗族自治州");
		tItem24.add("毕节地区");
		tItem24.add("黔东南苗族侗族自治州");
		tItem24.add("黔南布依族苗族自治州");
		children.put(i, tItem24);
		i++;
		groups.add("云南省");
		LinkedList<String> tItem25 = new LinkedList<String>();
		tItem25.add("昆明市");
		tItem25.add("曲靖市");
		tItem25.add("玉溪市");
		tItem25.add("保山市");
		tItem25.add("昭通市");
		tItem25.add("丽江市");
		tItem25.add("思茅市");
		tItem25.add("临沧市");
		tItem25.add("楚雄彝族自治州");
		tItem25.add("红河哈尼族彝族自治州");
		tItem25.add("文山壮族苗族自治州");
		tItem25.add("西双版纳傣族自治州");
		tItem25.add("大理白族自治州");
		tItem25.add("德宏傣族景颇族自治州");
		tItem25.add("怒江傈僳族自治州");
		tItem25.add("迪庆藏族自治州");
		children.put(i, tItem25);
		i++;
		groups.add("西藏");
		LinkedList<String> tItem26 = new LinkedList<String>();
		tItem26.add("拉萨市");
		tItem26.add("昌都地区");
		tItem26.add("山南地区");
		tItem26.add("日喀则地区");
		tItem26.add("那曲地区");
		tItem26.add("阿里地区");
		tItem26.add("林芝地区");
		children.put(i, tItem26);
		i++;
		groups.add("陕西省");
		LinkedList<String> tItem27 = new LinkedList<String>();
		tItem27.add("西安市");
		tItem27.add("铜川市");
		tItem27.add("宝鸡市");
		tItem27.add("咸阳市");
		tItem27.add("渭南市");
		tItem27.add("延安市");
		tItem27.add("汉中市");
		tItem27.add("榆林市");
		tItem27.add("安康市");
		tItem27.add("商洛市");
		children.put(i, tItem27);
		i++;
		groups.add("甘肃省");
		LinkedList<String> tItem28 = new LinkedList<String>();
		tItem28.add("兰州市");
		tItem28.add("嘉峪关市");
		tItem28.add("金昌市");
		tItem28.add("白银市");
		tItem28.add("天水市");
		tItem28.add("武威市");
		tItem28.add("张掖市");
		tItem28.add("平凉市");
		tItem28.add("酒泉市");
		tItem28.add("庆阳市");
		tItem28.add("安西市");
		tItem28.add("陇南市");
		tItem28.add("临夏回族自治州");
		tItem28.add("甘南藏族自治州");
		children.put(i, tItem28);
		i++;
		groups.add("青海省");
		LinkedList<String> tItem29 = new LinkedList<String>();
		tItem29.add("西宁市");
		tItem29.add("海东地区");
		tItem29.add("海北藏族自治州");
		tItem29.add("黄南藏族自治州");
		tItem29.add("海南藏族自治州");
		tItem29.add("果洛藏族自治州");
		tItem29.add("玉树藏族自治州");
		tItem29.add("海西蒙古族藏族自治州");
		children.put(i, tItem29);
		i++;
		groups.add("宁夏");
		LinkedList<String> tItem30 = new LinkedList<String>();
		tItem30.add("银川市");
		tItem30.add("石嘴山市");
		tItem30.add("吴忠市");
		tItem30.add("中卫市");
		children.put(i, tItem30);
		i++;
		groups.add("新疆");
		LinkedList<String> tItem31 = new LinkedList<String>();
		tItem31.add("乌鲁木齐市");
		tItem31.add("克拉玛依市");
		tItem31.add("吐鲁番地区");
		tItem31.add("哈密地区");
		tItem31.add("昌吉回族自治州");
		tItem31.add("博尔塔拉蒙古自治州");
		tItem31.add("巴音郭楞蒙古自治州");
		tItem31.add("阿克苏地区");
		tItem31.add("克孜勒苏柯尔克孜自治州");
		tItem31.add("喀什地区");
		tItem31.add("和田地区");
		tItem31.add("伊犁哈萨克自治州");
		tItem31.add("塔城地区");
		tItem31.add("阿勒泰地区");
		children.put(i, tItem31);
		i++;
		final String[] location=(String[])groups.toArray(new String[0]);
		earaListViewAdapter = new TextAdapter(context, groups,
				R.drawable.choose_item_selected,
				R.drawable.choose_eara_item_selector);
		earaListViewAdapter.setTextSize(17);
		earaListViewAdapter.setSelectedPositionNoNotify(tEaraPosition);
		regionListView.setAdapter(earaListViewAdapter);
		earaListViewAdapter
				.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(View view, int position) {
						if (position < children.size()) {
							place[0]=location[position];
							childrenItem.clear();
							childrenItem.addAll(children.get(position));
							plateListViewAdapter.notifyDataSetChanged();
						}
					}
				});
		if (tEaraPosition < children.size())
			childrenItem.addAll(children.get(tEaraPosition));
		plateListViewAdapter = new TextAdapter(context, childrenItem,
				R.drawable.choose_item_right,
				R.drawable.choose_plate_item_selector);
		plateListViewAdapter.setTextSize(15);
		plateListViewAdapter.setSelectedPositionNoNotify(tBlockPosition);
		plateListView.setAdapter(plateListViewAdapter);
		plateListViewAdapter
				.setOnItemClickListener(new TextAdapter.OnItemClickListener() {

					@Override
					public void onItemClick(View view, final int position) {
						
						showString = childrenItem.get(position);
						place[1]=showString;
						if (mOnSelectListener != null) {
							
							mOnSelectListener.getValue(showString);
						}

					}
				});
		if (tBlockPosition < childrenItem.size())
			showString = childrenItem.get(tBlockPosition);
		if (showString.contains("不限")) {
			showString = showString.replace("不限", "");
		}
		
		setDefaultSelect();

	}

	public void setDefaultSelect() {
		regionListView.setSelection(tEaraPosition);
		plateListView.setSelection(tBlockPosition);
	}

	public String getShowText() {
		return "选择区域";
	}

	public void setOnSelectListener(OnSelectListener onSelectListener) {
		mOnSelectListener = onSelectListener;
	}

	public interface OnSelectListener {
		public void getValue(String showText);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}
}
