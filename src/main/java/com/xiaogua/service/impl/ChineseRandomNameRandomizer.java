package com.xiaogua.service.impl;

import java.util.Random;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.Validate;

import com.xiaogua.service.InterfaceRandomizer;

/**
 * 中文随机姓名字符串
 */
public class ChineseRandomNameRandomizer implements InterfaceRandomizer<String> {
	// 常用姓氏
	private static final String[] commonSingleNameArr = new String[] { "李", "王", "张", "刘", "陈", "杨", "赵", "黄", "周", "吴",
			"徐", "孙", "胡", "朱", "高", "林", "何", "郭", "马", "罗", "梁", "宋", "郑", "谢", "韩", "唐", "冯", "于", "董", "萧", "程",
			"曹", "袁", "邓", "许", "傅", "沈", "曾", "彭", "吕", "苏", "卢", "蒋", "蔡", "贾", "丁", "魏", "薛", "叶", "阎", "余", "潘",
			"杜", "戴", "夏", "钟", "汪", "田", "任", "姜", "范", "方", "石", "姚", "谭", "廖", "邹", "熊", "金", "陆", "郝", "孔", "白",
			"崔", "康", "毛", "邱", "秦", "江", "史", "顾", "侯", "邵", "孟", "龙", "万", "段", "漕", "钱", "汤", "尹", "黎", "易", "常",
			"武", "乔", "贺", "赖", "龚", "文", "庞", "樊", "兰", "殷", "施", "陶", "洪", "翟", "安", "颜", "倪", "严", "牛", "温", "芦",
			"季", "俞", "章", "鲁", "葛", "伍", "韦", "申", "尤", "毕", "聂", "丛", "焦", "向", "柳", "邢", "路", "岳", "齐", "沿", "梅",
			"莫", "庄", "辛", "管", "祝", "左", "涂", "谷", "祁", "时", "舒", "耿", "牟", "卜", "路", "詹", "关", "苗", "凌", "费", "纪",
			"靳", "盛", "童", "甄", "项", "曲", "成", "游", "阳", "裴", "席", "卫", "查", "屈", "鲍", "位", "覃", "霍", "翁", "隋", "植",
			"甘", "景", "薄", "单", "包", "司", "柏", "宁", "柯", "阮", "桂", "闵", "解", "强", "柴", "华", "车", "冉", "房", "边", "辜",
			"吉", "饶", "刁", "瞿", "戚", "丘", "古", "米", "池", "滕", "晋", "苑", "邬", "臧", "畅", "宫", "来", "嵺", "苟", "全", "褚",
			"廉", "简", "娄", "盖", "符", "奚", "木", "穆", "党", "燕", "郎", "邸", "冀", "谈", "姬", "屠", "连", "郜", "晏", "栾", "郁",
			"商", "蒙", "计", "喻", "揭", "窦", "迟", "宇", "敖", "糜", "鄢", "冷", "卓", "花", "仇", "艾", "蓝", "都", "巩", "稽", "井",
			"练", "仲", "乐", "虞", "卞", "封", "竺", "冼", "原", "官", "衣", "楚", "佟", "栗", "匡", "宗", "应", "台", "巫", "鞠", "僧",
			"桑", "荆", "谌", "银", "扬", "明", "沙", "薄", "伏", "岑", "习", "胥", "保", "和", "蔺" };
	// 常见复姓
	private static final String[] commonCompoundSurNameArr = new String[] { "欧阳", "太史", "端木", "上官", "司马", "东方", "独孤",
			"南宫", "万俟", "夏侯", "诸葛", "尉迟", "公羊", "赫连", "宇文", "司徒", "公西", "西门", "慕容", "令狐", "长孙", "司空", "鲜于", "轩辕", "东方",
			"呼延" };

	// 常见女生名
	private static final String[] commonSecondGirlNameArr = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽"
			.split("");
	// 常见男生名
	private static final String[] commonSecondBoyNameArr = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘"
			.split("");
	// 名字最小长度
	private int minNameLength = 1;
	// 名字最大长度
	private int maxNameLength = 2;
	// 是否固定长度
	private boolean isFixedLength = false;
	// 默认单姓(0单姓 1复姓 -1不限制)
	private int nameType = 0;
	// 性别(1男0女-1不限制)
	private int sex = 1;
	private Random random;
	private StringBuffer sb;

	public static ChineseRandomNameRandomizer aNewSingleNameRandomizer(int minNameLength, int maxNameLength, int sex) {
		return new ChineseRandomNameRandomizer(minNameLength, maxNameLength, 0, sex);
	}

	public static ChineseRandomNameRandomizer aNewCompoundSurNameRandomizer(int minNameLength, int maxNameLength,
			int sex) {
		return new ChineseRandomNameRandomizer(minNameLength, maxNameLength, 1, sex);
	}

	public static ChineseRandomNameRandomizer aNewMixNameRandomizer(int minNameLength, int maxNameLength, int sex) {
		return new ChineseRandomNameRandomizer(minNameLength, maxNameLength, -1, sex);
	}

	public ChineseRandomNameRandomizer() {
		this.random = new Random();
		this.sb = new StringBuffer(16);
	}

	public ChineseRandomNameRandomizer(int minNameLength, int maxNameLength, int nameType, int sex) {
		super();
		checkValue(minNameLength, maxNameLength, nameType, sex);
		this.minNameLength = minNameLength;
		this.maxNameLength = maxNameLength;
		if (this.minNameLength == this.maxNameLength) {
			isFixedLength = true;
		}
		this.nameType = nameType;
		this.sex = sex;
		this.random = new Random();
		this.sb = new StringBuffer(16);
	}

	public String getRandomValue() {
		this.sb.setLength(0);
		int randomValue = this.nameType;
		if (randomValue == -1) {
			randomValue = this.random.nextInt(2);
		}
		if (randomValue == 0) {
			// 单姓
			this.sb.append(commonSingleNameArr[this.random.nextInt(commonSingleNameArr.length)]);
		} else {
			// 复姓
			this.sb.append(commonCompoundSurNameArr[this.random.nextInt(commonCompoundSurNameArr.length)]);
		}
		int randomLen = this.minNameLength;
		if (!this.isFixedLength) {
			randomLen = RandomUtils.nextInt(this.minNameLength, this.maxNameLength + 1);
		}
		for (int i = 0; i < randomLen; i++) {
			randomValue = sex;
			if (randomValue == -1) {
				// 混合
				randomValue = this.random.nextInt(2);
			}
			if (randomValue == 0) {
				// 女
				this.sb.append(commonSecondGirlNameArr[this.random.nextInt(commonSecondGirlNameArr.length)]);
			} else if (randomValue == 1) {
				// 男
				this.sb.append(commonSecondBoyNameArr[this.random.nextInt(commonSecondBoyNameArr.length)]);
			}
		}
		return this.sb.toString();
	}

	private void checkValue(int minNameLength, int maxNameLength, int nameType, int sex) {
		Validate.isTrue(minNameLength >= 0, "minNameLength must great than 0");
		Validate.isTrue(maxNameLength >= minNameLength, "maxNameLength must great than minNameLength");
		Validate.isTrue(nameType >= -1 && nameType <= 1, "nameType value range [-1,1]");
		Validate.isTrue(sex >= -1 && sex <= 1, "sex value range [-1,1]");
	}

}
