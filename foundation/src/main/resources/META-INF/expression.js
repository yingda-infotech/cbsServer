/**
 * 获取字符串长度
 * @param x
 * @returns
 */
function strlen(x)
{
	return x.length()
}

/**
 * 切割字符串
 * @param str
 * @param x
 * @param y
 * @returns
 */
function substr(str,x,y)
{
   x = (x < 0) ? 0 : x;
   y = (y < 0) ? 0 : y;
    
   x = (x > str.length()) ? str.length() : x;
   y = (y > str.length()) ? str.length() : y;
    
   y = (y < x) ? x : y;
   return str.substring(x,y);
}

/**
 * 传给可变数量的参数，返回第一个不为空的参数
 * @returns
 */
function choose(){
  for( var i = 0; i < arguments.length; i++ ){  
    if (arguments[i] !== null && arguments[i] !== undefined && arguments[i] !== '') { 
    	return arguments[i];
    }         
  }  
}

/**
 * 两个数相加计算，返回相加的结果
 * @param x
 * @param y
 */
function add(x,y){
	if (x == "" || y == "") { // 如果有参数为空就返回空
		 return 0;
	}
	//如果不是数字，直接返回0
	 if(isNaN(x)||isNaN(y)){
		 return 0;
	 }
	return parseFloat(x) + parseFloat(y);
}

/**
 * 把字符转换为int型，如果不是数字型，返回0
 * @param number 字符型数据
 * @returns int型数据
 */
function toInt(number){
	if (number == "") { // 如果有参数为空就返回空
		 return 0;
	}
	 //isNaN 如果是数字则返回 false
	 if(isNaN(number)){
		 return 0.0;
	 }else{
		 return parseInt(number);
	 }
}

/**
 * atoi  字符串转整数,atoi是旧的转换函数名，继续兼容
 * 把字符转换为int型，如果不是数字型，返回0
 * @param number 字符型数据
 * @returns int型数据
 */
function atoi(number){
	if (number == "") { // 如果有参数为空就返回空
		 return 0;
	}
	return toInt(number);
}

/**
 * Number() 函数把对象的值转换为数字。
 * @param x
 * @returns
 */
function tonum(x){
	if (number == "") { // 如果有参数为空就返回空
		 return 0;
	}
	if(isNaN(number)){
	  return 0.0;
	}else{
	  return Number(x);
	}
}

/**
 * 把字符转换为double型，如果不是数字型，返回0
 * @param number 字符型数据
 * @returns double型数据
 */
function toDouble(number){
	if (number == "") { // 如果有参数为空就返回空
		 return 0;
	}
	 //isNaN 如果是数字则返回 false
	 if(isNaN(number)){
		 return 0.0;
	 }else{
		 return parseFloat(number);
	 }
}

/**
 *
p1 = 1  求日期加天数    
p2　字段名, p3 整数字段名或常量

p1 = 2  求日期减天数    
p2　字段名, p3 整数字段名或常量

p1 = 4  求日期加上num年    
p2　字段名, p3 整数字段名或常量

日期加一 ＝ date_f （1,  date1 ,  num   ）
日期减一 ＝ date_f （2,  date1 ,  num   ）
日期年加num ＝ date_f （4,  date1 ,  num   ）

 * @param p1
 * @param p2
 * @param p3
 */
function date_f(p1, p2, p3) {
	
	var tmp = new Date(p2);
	
	if (p1 == 1) {
		if (p2 == "" || p3 == "") { // 如果有参数为空就 返回空
			return "";
		}
		tmp.setDate(tmp.getDate()+p3);		
		var tempDate = new Date(tmp);
		var month = tempDate.getMonth()+1;
		var day = tempDate.getDate();
		if((month+"").length == 1){
			month = "0"+month;
		}
		if((day+"").length == 1){
			day = "0"+day;  
		}
		return tempDate.getFullYear()+"-"+month+"-"+day;
	}
	if (p1 == 2) {
		if (p2 == "" || p3 == "") { // 如果有参数为空就返回空
			return "";
		}
		p3 = -p3;
		tmp.setDate(tmp.getDate()+p3);		
		var tempDate = new Date(tmp);
		var month = tempDate.getMonth()+1;
		var day = tempDate.getDate();
		if((month+"").length == 1){
			month = "0"+month;
		}
		if((day+"").length == 1){
			day = "0"+day;
		}
		return tempDate.getFullYear()+"-"+month+"-"+day;
	}
	if (p1 == 4) {
		if (p2 == "" || p3 == "") { // 如果有参数为空就返回空
			return "";
		}
		if(p2!=""){
			if(p2.substring(0,4)=="0000"){
				return "0000-00-00";
			}
			var year=parseInt(p2.substring(0,4))+parseInt(p3);
			var a=p2.substring(4,p2.length);
			var date = year+a;
			return date;
		}
	}
}

/**
 * 计算天数差的函数，通用  
 * 后面的参数减去前面的参数，如果前面的日期大，需要返回负数天数
 * @param sDate1
 * @param sDate2
 * @returns 返回天数差
 */
function  dateDiff(sDate1,  sDate2){    //sDate1和sDate2是2006-12-18格式  
	if (sDate1 == "" || sDate2 == "") { // 如果有参数为空就返回空
		 return 0;
	}
    var  aDate,  oDate1,  oDate2,  iDays  
    aDate  =  sDate1.split("-")  
    oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为12-18-2006格式  
    aDate  =  sDate2.split("-")  
    oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
//    iDays  =  parseInt(Math.abs(oDate1 - oDate2)/1000/60/60/24)    //把相差的毫秒数转换为天数
    iDays  =  parseInt((oDate1 - oDate2)/1000/60/60/24)    //把相差的毫秒数转换为天数
    return  iDays  
}    

/**
 * 判断字符串包含
 * str1 中包含 str2，则返回 1 ， 否则返回 0
 */
function strstr(str1,str2){
	if (str1 == "" || str2 == "") { // 如果有参数为空就返回空
		 return 0;
	}
	var bool = str1.indexOf(str2);
	if(bool == -1){
		return 0;
	}else{
		return 1;
	}
}

/**
 * 整数取模
 * @param num1 整型变量或整数
 * @param num2 整型变量或整数
 */
function mod(num1,num2){
	if (num1 == "" || num2 == "") { // 如果有参数为空就返回空
		 return 0;
	}
	//如果不是数字，直接返回0
	 if(isNaN(num1)||isNaN(num2)){
		 return 0;
	 }
	return num1%num2;
}


