技术文档：
主界面中：
1.当一开是加载程序时，需要将程序中的数据库靠不到本地文件中，方便程序再处理数据库时的高效，然后初始化一个全局的数据库操作对象，防止多次初始化导致的同步问题：
	(new DBCopyer(this)).open();
	
		// 初始化数据操作类
		Utils.dbOperate = new DBOperate();


2.有登录和切换两种模式，点击不同的模式显示不同的视图，当点击切换试图以后我们需要在监听的方法中处理对应试图的显示和隐藏
代码如下：
// @Override 注册和登陆界面切换监听
	public void onCheckedChanged(CompoundButton buttonView, boolean       isChecked) {
		switch (buttonView.getId()) {
			case R.id.btn_log:
				llyoutLogin.setVisibility(View.VISIBLE);
				llyoutRegister.setVisibility(View.GONE);
				break;
			case R.id.btn_register:
				llyoutRegister.setVisibility(View.VISIBLE);
				llyoutLogin.setVisibility(View.GONE);
				break;
	}
	
	3.用户注册和登录的处理过错中，我们通过简单的判断用户所填入信息的完整信来进行处理，这样能防止空数据错误，使得用户信息完整
		if(name.equals("")||password.equals("")||pasDefine.equals("")||phone.equals("")){
				UtilManage.showMessage(this, "输入信息不能为空");	
			}else if(!pasDefine.equals(password)){
				UtilManage.showMessage(this, "两次密码输入不一样");	
			}
			
	4最后的登录处理，通过用户填入的用户名和密码，查询user数据库，如果有返回的数据则表示用户存在，如果为空提示错误信息。
	if(Utils.dbOperate.isExistUser(userEntity)!=null){
					Utils.userEntity=Utils.dbOperate.isExistUser(userEntity);
					UtilManage.jumpActivity(this, ContollerActivity.class);
				}else{
					UtilManage.showMessage(this, "用户不存在");	
				}
				
	订单和物品显示界面中：
	1.货物的显示控件我们运用android中自带的listview控件，它是一个能上下滑动可加载相同布局的视图，方便我们展现我们的货物和订单数据。
	private ListView ltView = (ListView) findViewById(R.id.ltview);
	
	2.得到一个listview控件后，需要将数据库中得到的相关数据展现到界面中，所以需要创建一个listview能识别的视图数据，官方文档提供的方法提示我们需要封装一个adapter对象，在这个对象中重写getView（）方法，将自定义的layout布局视图初始并且返回给adapter对象，然后当listview加载数据时，会多次重复获取getview（）中的视图，通过方法中返回的position当前位置，将数据设置到控件中。代码如下：
	public View getView(final int position, View convertView, ViewGroup parent) {
	加载布局文件，然后得到布局文件中的不同控件对象
		ViewHolder viewHolder = null;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = View.inflate(context, R.layout.llyout_car_item, null);
			viewHolder.tvMsg = (TextView) convertView.findViewById(R.id.tv_msg);
				convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		然后是根据position位置得到数据，设在到控件上
		HuoWuEntity entity = ltHuoWu.get(position);
		MyOnClickListener onClickListener = new MyOnClickListener(entity);
		viewHolder.tvMsg.setText(getMsg(entity));
		
		
3.当封装的数据封装好以后，需要设置给滑动控件listview，这是需要调用listview自带的setAdapter（）方法：
	ltHuoWuEntities = Utils.dbOperate.getHuoWuEntities();
	ltView.setAdapter(new HuoWuAdapter(this, ltHuoWuEntities, false));
	
	4.当视图显示出来以后，需要监听每一次用户点击的货物信息，然后处理当前的货物信息，比如显示当前货物的详细数据，这是需要添加一个OnItemClickListener监听器，这样当用户点击每一个item视图时，会触发它的回调方法nItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)这个方法中我们可以处理显示具体的信息：
		// 给listview的设置监听器，当点击每行的时候显示信息÷
		ltView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				String msgShow = "";
				if (rbtnDingDan.isChecked()) {
					DingDanEntity danEntity = ltDingDanEntities.get(position);
					msgShow = danEntity.getShow();
					// 当前显示货物
				} else if (rBtnHuoWu.isChecked()) {
					HuoWuEntity huoWuEntity = ltHuoWuEntities.get(position);
					msgShow = huoWuEntity.getShow();
				}
				new DialogShowMsg(UserActivity.this, msgShow).show();
			}
		});
		
		
5除了点击处理以外，当管理员进入时，可以通过长按事件来处理删除功能，这是需要给listview加入一个长按监听OnItemLongClickListener，通过设在这个监听，当长按点击控件的时候，会回调onItemLongClick(AdapterView<?> arg0, View arg1,int arg2, long arg3)方法，更具arg2得到当前点击位置，得到相关的数据，处理删除事件：

		ltView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (rbtnDingDan.isChecked()) {
					Utils.dbOperate.deleteDingDan(ltDingDanEntities.get(arg2));
				}
				return true;
			}
		});
	
	6.同样的订单视图中我们页采用了同样的方法，首先封装一个订单显示dapter，然调用setAdapter（）方法，让listvie显示出来，设在点击监听和长按监听来处理点击显示详情，长按删除处理。
	
下面是基本的技术文档：
1.Android基本属性
http://www.cnblogs.com/awenhome/archive/2011/12/29/2305813.html
重点属性：（alt+/自动提示功能）
android:textColor                   //设置文本颜色
android:textSize                      //设置文字大小，推荐度量单位”sp”，如”15sp”

android:layout_marginBottom              离某元素底边缘的距离

android:layout_marginLeft                 离某元素左边缘的距离

android:layout_marginRight                离某元素右边缘的距离

android:layout_marginTop                  离某元素上边缘的距离

android:textSize                      //设置文字大小，推荐度量单位”sp”，如”15sp”

android:layout_centerHrizontal         //水平居中

android:layout_centerVertical           //垂直居中

android:layout_centerInparent          //相对于父元素完全居中

2.listview的重点用法：(自定义格式)
http://www.2cto.com/kf/201402/280719.html

3.数据库操作
	添加数据：insert into MSG(TITLE,CONTENT,LOOK,TIME) values('s','1111','20012','dalian')
	删除数据：delete from QICHE where id=1
	修改数据：update MY_PRACTISE set NAME='d',TIME='d',CONTENT='d' where ID=5
	查找数据：select * from wubi_tb where character='工'

4.给控件设置监听
	一种在布局文件中
	android:onClick="onClick"
	一种在代码设置：
	((RadioButton) findViewById(R.id.btn_buyer)).setOnClickListener(this);
	
	Listview设置监听item：OnItemClickListener
	Listview给item中的控件设置监听

5．界面跳转方法
	Intent intent = new Intent(this, cls);
	startActivity(intent);

6.弹出dialog框
	Dialog d=new Dialog(…);
	d.show();
	关闭弹出框
	d.dissmiss();

7.给界面设置布局文件：
	setContentView(R.layout.activity_signin);	

8.得到初始化的控件：
	EditText etvName = (EditText) findViewById(R.id.etv_name);

9.处理监听事件：
	
	implements OnClickListener(继承监听接口)
		实现方法
		public void onClick(View v) {
			//得到点击控件的id
			int id=v.getId();
			判断如果id等你要你处理的id
			if(id=...){
			相应处理
			DO();
			}
		}

10.弹出文字信息：
	Toast.makeText(context, “信息”, Toast.LENGTH_LONG).show();

	
