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
	private String showString = "ѡ�����";
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
			if (childrenItem.get(j).replace("����", "").equals(showBlock.trim())) {
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
			groups.add(i+"��");
			LinkedList<String> tItem = new LinkedList<String>();
			for(int j=0;j<15;j++){
				
				tItem.add(i+"��"+j+"��");
				
			}
			children.put(i, tItem);
		}*/
		int i=0;
		int j=0;
		groups.add("������");
		LinkedList<String> tItem = new LinkedList<String>();
		tItem.add("������");
		children.put(i, tItem);
		i++;
		groups.add("�����");
		LinkedList<String> tItem2 = new LinkedList<String>();
		tItem2.add("�����");
		children.put(i, tItem2);
		i++;
		groups.add("�ӱ�ʡ");
		LinkedList<String> tItem3 = new LinkedList<String>();
		tItem3.add("ʯ��ׯ��");
		tItem3.add("��ɽ��");
		tItem3.add("�ػʵ���");
		tItem3.add("������");
		tItem3.add("��̨��");
		tItem3.add("������");
		tItem3.add("�żҿ���");
		tItem3.add("�е���");
		tItem3.add("������");
		tItem3.add("�ȷ���");
		tItem3.add("��ˮ��");
		children.put(i, tItem3);
		i++;
		groups.add("ɽ��ʡ");
		LinkedList<String> tItem4 = new LinkedList<String>();
		tItem4.add("̫ԭ��");
		tItem4.add("��ͬ��");
		tItem4.add("��Ȫ��");
		tItem4.add("������");
		tItem4.add("������");
		tItem4.add("˷����");
		tItem4.add("������");
		tItem4.add("�˳���");
		tItem4.add("������");
		tItem4.add("�ٷ���");
		tItem4.add("������");
		children.put(i, tItem4);
		i++;
		groups.add("���ɹ�");
		LinkedList<String> tItem5 = new LinkedList<String>();
		tItem5.add("���ͺ�����");
		tItem5.add("��ͷ��");
		tItem5.add("�ں���");
		tItem5.add("�����");
		tItem5.add("ͨ����");
		tItem5.add("������˹��");
		tItem5.add("���ױ�����");
		tItem5.add("�����׶���");
		tItem5.add("�����첼��");
		tItem5.add("�ٷ���");
		tItem5.add("���ֹ�����");
		tItem5.add("��������");
		children.put(i, tItem5);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem6 = new LinkedList<String>();
		tItem6.add("������");
		tItem6.add("������");
		tItem6.add("��ɽ��");
		tItem6.add("��˳��");
		tItem6.add("��Ϫ��");
		tItem6.add("������");
		tItem6.add("������");
		tItem6.add("Ӫ����");
		tItem6.add("������");
		tItem6.add("������");
		tItem6.add("�̽���");
		tItem6.add("������");
		tItem6.add("������");
		tItem6.add("��«����");
		children.put(i, tItem6);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem7 = new LinkedList<String>();
		tItem7.add("������");
		tItem7.add("������");
		tItem7.add("��ƽ��");
		tItem7.add("��Դ��");
		tItem7.add("ͨ����");
		tItem7.add("��ɽ��");
		tItem7.add("��ԭ��");
		tItem7.add("�׳���");
		tItem7.add("�ӱ߳�����������");
		children.put(i, tItem7);
		i++;
		groups.add("������");
		LinkedList<String> tItem8 = new LinkedList<String>();
		tItem8.add("��������");
		tItem8.add("���������");
		tItem8.add("������");
		tItem8.add("�׸���");
		tItem8.add("˫Ѽɽ��");
		tItem8.add("������");
		tItem8.add("������");
		tItem8.add("Ӫ����");
		tItem8.add("��̨����");
		tItem8.add("ĵ������");
		tItem8.add("�ں���");
		tItem8.add("�绯��");
		tItem8.add("���˰������");
		children.put(i, tItem8);
		i++;
		groups.add("�Ϻ���");
		LinkedList<String> tItem9 = new LinkedList<String>();
		tItem9.add("�Ϻ���");
		children.put(i, tItem9);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem10 = new LinkedList<String>();
		tItem10.add("�Ͼ���");
		tItem10.add("������");
		tItem10.add("������");
		tItem10.add("������");
		tItem10.add("��ͨ��");
		tItem10.add("���Ƹ���");
		tItem10.add("������");
		tItem10.add("�γ���");
		tItem10.add("������");
		tItem10.add("����");
		tItem10.add("̩����");
		tItem10.add("��Ǩ��");
		children.put(i, tItem10);
		i++;
		groups.add("�㽭ʡ");
		LinkedList<String> tItem11 = new LinkedList<String>();
		tItem11.add("������");
		tItem11.add("������");
		tItem11.add("������");
		tItem11.add("������");
		tItem11.add("������");
		tItem11.add("������");
		tItem11.add("����");
		tItem11.add("������");
		tItem11.add("��ɽ��");
		tItem11.add("̨����");
		tItem11.add("��ˮ��");
		children.put(i, tItem11);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem12 = new LinkedList<String>();
		tItem12.add("�Ϸ���");
		tItem12.add("�ߺ���");
		tItem12.add("������");
		tItem12.add("������");
		tItem12.add("��ɽ��");
		tItem12.add("������");
		tItem12.add("ͭ����");
		tItem12.add("������");
		tItem12.add("��ɽ��");
		tItem12.add("������");
		tItem12.add("������");
		tItem12.add("������");
		tItem12.add("������");
		tItem12.add("������");
		tItem12.add("������");
		tItem12.add("������");
		tItem12.add("������");
		children.put(i, tItem12);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem13 = new LinkedList<String>();
		tItem13.add("������");
		tItem13.add("������");
		tItem13.add("������");
		tItem13.add("������");
		tItem13.add("Ȫ����");
		tItem13.add("������");
		tItem13.add("��ƽ��");
		tItem13.add("������");
		tItem13.add("������");
		children.put(i, tItem13);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem14 = new LinkedList<String>();
		tItem14.add("�ϲ���");
		tItem14.add("��������");
		tItem14.add("Ƽ����");
		tItem14.add("�Ž���");
		tItem14.add("������");
		tItem14.add("ӥ̶��");
		tItem14.add("������");
		tItem14.add("������");
		tItem14.add("�˴���");
		tItem14.add("������");
		tItem14.add("������");
		children.put(i, tItem14);
		i++;
		groups.add("ɽ��ʡ");
		LinkedList<String> tItem15 = new LinkedList<String>();
		tItem15.add("������");
		tItem15.add("�ൺ��");
		tItem15.add("�Ͳ���");
		tItem15.add("��ׯ��");
		tItem15.add("��Ӫ��");
		tItem15.add("��̨��");
		tItem15.add("Ϋ����");
		tItem15.add("������");
		tItem15.add("̩����");
		tItem15.add("������");
		tItem15.add("������");
		tItem15.add("������");
		tItem15.add("������");
		tItem15.add("������");
		tItem15.add("�ĳ���");
		tItem15.add("������");
		tItem15.add("������");
		children.put(i, tItem15);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem16 = new LinkedList<String>();
		tItem16.add("֣����");
		tItem16.add("������");
		tItem16.add("������");
		tItem16.add("ƽ��ɽ��");
		tItem16.add("������");
		tItem16.add("�ױ���");
		tItem16.add("������");
		tItem16.add("������");
		tItem16.add("�����");
		tItem16.add("�����");
		tItem16.add("�����");
		tItem16.add("����Ͽ��");
		tItem16.add("������");
		tItem16.add("������");
		tItem16.add("������");
		tItem16.add("�ܿ���");
		tItem16.add("פ�����");
		children.put(i, tItem16);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem17 = new LinkedList<String>();
		tItem17.add("�人��");
		tItem17.add("��ʯ��");
		tItem17.add("ʮ����");
		tItem17.add("�˲���");
		tItem17.add("�差��");
		tItem17.add("������");
		tItem17.add("������");
		tItem17.add("Т����");
		tItem17.add("������");
		tItem17.add("�Ƹ���");
		tItem17.add("������");
		tItem17.add("������");
		tItem17.add("��ʩ����������������");
		children.put(i, tItem17);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem18 = new LinkedList<String>();
		tItem18.add("��ɳ��");
		tItem18.add("������");
		tItem18.add("��̶��");
		tItem18.add("������");
		tItem18.add("������");
		tItem18.add("������");
		tItem18.add("������");
		tItem18.add("�żҽ���");
		tItem18.add("������");
		tItem18.add("������");
		tItem18.add("������");
		tItem18.add("������");
		tItem18.add("¦����");
		tItem18.add("��������������������");
		children.put(i, tItem18);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem19 = new LinkedList<String>();
		tItem19.add("������");
		tItem19.add("�ع���");
		tItem19.add("������");
		tItem19.add("�麣��");
		tItem19.add("��ͷ��");
		tItem19.add("��ɽ��");
		tItem19.add("������");
		tItem19.add("տ����");
		tItem19.add("ï����");
		tItem19.add("������");
		tItem19.add("������");
		tItem19.add("÷����");
		tItem19.add("��β��");
		tItem19.add("��Դ��");
		tItem19.add("������");
		tItem19.add("��Զ��");
		tItem19.add("��ݸ��");
		tItem19.add("��ɽ��");
		children.put(i, tItem19);
		i++;
		groups.add("����");
		LinkedList<String> tItem20 = new LinkedList<String>();
		tItem20.add("������");
		tItem20.add("������");
		tItem20.add("������");
		tItem20.add("������");
		tItem20.add("������");
		tItem20.add("���Ǹ���");
		tItem20.add("������");
		tItem20.add("�����");
		tItem20.add("������");
		tItem20.add("��ɫ��");
		tItem20.add("������");
		tItem20.add("�ӳ���");
		tItem20.add("������");
		tItem20.add("������");
		children.put(i, tItem20);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem21 = new LinkedList<String>();
		tItem21.add("������");
		tItem21.add("������");
		children.put(i, tItem21);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem22 = new LinkedList<String>();
		tItem22.add("������");
		children.put(i, tItem22);
		i++;
		groups.add("�Ĵ�ʡ");
		LinkedList<String> tItem23 = new LinkedList<String>();
		tItem23.add("�ɶ���");
		tItem23.add("�Թ���");
		tItem23.add("��֦����");
		tItem23.add("������");
		tItem23.add("������");
		tItem23.add("������");
		tItem23.add("��Ԫ��");
		tItem23.add("������");
		tItem23.add("�ڽ���");
		tItem23.add("��ɽ��");
		tItem23.add("�ϳ���");
		tItem23.add("üɽ��");
		tItem23.add("�˱���");
		tItem23.add("�㰲��");
		tItem23.add("������");
		tItem23.add("�Ű���");
		tItem23.add("������");
		tItem23.add("������");
		tItem23.add("���Ӳ���Ǽ��������");
		tItem23.add("���β���������");
		tItem23.add("��ɽ����������");
		children.put(i, tItem23);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem24 = new LinkedList<String>();
		tItem24.add("������");
		tItem24.add("����ˮ��");
		tItem24.add("������");
		tItem24.add("��˳��");
		tItem24.add("ͭ����");
		tItem24.add("ǭ���ϲ���������������");
		tItem24.add("�Ͻڵ���");
		tItem24.add("ǭ�������嶱��������");
		tItem24.add("ǭ�ϲ���������������");
		children.put(i, tItem24);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem25 = new LinkedList<String>();
		tItem25.add("������");
		tItem25.add("������");
		tItem25.add("��Ϫ��");
		tItem25.add("��ɽ��");
		tItem25.add("��ͨ��");
		tItem25.add("������");
		tItem25.add("˼é��");
		tItem25.add("�ٲ���");
		tItem25.add("��������������");
		tItem25.add("��ӹ���������������");
		tItem25.add("��ɽ׳������������");
		tItem25.add("��˫���ɴ���������");
		tItem25.add("�������������");
		tItem25.add("�º���徰����������");
		tItem25.add("ŭ��������������");
		tItem25.add("�������������");
		children.put(i, tItem25);
		i++;
		groups.add("����");
		LinkedList<String> tItem26 = new LinkedList<String>();
		tItem26.add("������");
		tItem26.add("��������");
		tItem26.add("ɽ�ϵ���");
		tItem26.add("�տ������");
		tItem26.add("��������");
		tItem26.add("�������");
		tItem26.add("��֥����");
		children.put(i, tItem26);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem27 = new LinkedList<String>();
		tItem27.add("������");
		tItem27.add("ͭ����");
		tItem27.add("������");
		tItem27.add("������");
		tItem27.add("μ����");
		tItem27.add("�Ӱ���");
		tItem27.add("������");
		tItem27.add("������");
		tItem27.add("������");
		tItem27.add("������");
		children.put(i, tItem27);
		i++;
		groups.add("����ʡ");
		LinkedList<String> tItem28 = new LinkedList<String>();
		tItem28.add("������");
		tItem28.add("��������");
		tItem28.add("�����");
		tItem28.add("������");
		tItem28.add("��ˮ��");
		tItem28.add("������");
		tItem28.add("��Ҵ��");
		tItem28.add("ƽ����");
		tItem28.add("��Ȫ��");
		tItem28.add("������");
		tItem28.add("������");
		tItem28.add("¤����");
		tItem28.add("���Ļ���������");
		tItem28.add("���ϲ���������");
		children.put(i, tItem28);
		i++;
		groups.add("�ຣʡ");
		LinkedList<String> tItem29 = new LinkedList<String>();
		tItem29.add("������");
		tItem29.add("��������");
		tItem29.add("��������������");
		tItem29.add("���ϲ���������");
		tItem29.add("���ϲ���������");
		tItem29.add("�������������");
		tItem29.add("��������������");
		tItem29.add("�����ɹ������������");
		children.put(i, tItem29);
		i++;
		groups.add("����");
		LinkedList<String> tItem30 = new LinkedList<String>();
		tItem30.add("������");
		tItem30.add("ʯ��ɽ��");
		tItem30.add("������");
		tItem30.add("������");
		children.put(i, tItem30);
		i++;
		groups.add("�½�");
		LinkedList<String> tItem31 = new LinkedList<String>();
		tItem31.add("��³ľ����");
		tItem31.add("����������");
		tItem31.add("��³������");
		tItem31.add("���ܵ���");
		tItem31.add("��������������");
		tItem31.add("���������ɹ�������");
		tItem31.add("���������ɹ�������");
		tItem31.add("�����յ���");
		tItem31.add("�������տ¶�����������");
		tItem31.add("��ʲ����");
		tItem31.add("�������");
		tItem31.add("���������������");
		tItem31.add("���ǵ���");
		tItem31.add("����̩����");
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
		if (showString.contains("����")) {
			showString = showString.replace("����", "");
		}
		
		setDefaultSelect();

	}

	public void setDefaultSelect() {
		regionListView.setSelection(tEaraPosition);
		plateListView.setSelection(tBlockPosition);
	}

	public String getShowText() {
		return "ѡ������";
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
