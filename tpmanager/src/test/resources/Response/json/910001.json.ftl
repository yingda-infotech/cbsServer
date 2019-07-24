<#macro initComma level=0>
<#if level=0>
<#assign commaArray=["true"]>
<#else>
<#assign commaArray=commaArray[0..<level]+["true"]>
</#if>
</#macro>
<#macro setComma clvl=0>
<#if clvl=0>
<#assign commaArray=["false"]>
<#else>
<#assign commaArray=commaArray[0..<clvl]+["false"]>
</#if>
</#macro>
<#macro ele name value=name?lower_case?eval level=0>
<#if value??>
<#if commaArray[level]="false">
,<#rt>
</#if>
"${name}":"${value}"<#rt>
<@setComma level/>
</#if>
</#macro>
<#macro grp name lvl=0>
<#lt>
<#if commaArray[lvl]="false">
,<#rt>
</#if>
<#local nextlvl=lvl+1>
<#lt>"${name}":{<@initComma nextlvl/><#nested nextlvl>}<#rt>
<@setComma lvl/>
</#macro>
<#macro lst name value level>
<#if value?? && (value?size>0)>
<#if commaArray[level]="false">
<#lt>,<#rt>
</#if>
<#local nextlvl=level+1>
"${name}":[
<#list value as v>
{
<@initComma nextlvl/>
<#nested v nextlvl>
}
<#if v_has_next>,</#if>
</#list>
]
<@setComma level/>
</#if>
</#macro>
<#macro root>
<@compress single_line=true>
{
  <@initComma 0 />
  <#nested>
}
</@compress>
</#macro>
<@root>
	<@grp "Transaction",0 >
	  <@grp "Comdict",1>
	    <@ele "BankCode",bankcode,2/>
	    <@ele "Brc",brc,2/>
	    <@ele "BrcName0",brcname0,2/>
	    <@grp "FlagRef",2>
	      <@ele "FrontCtrlFlag",frontctrlflag,3/>
	      <@ele "AuthFlag",authflag,3/>
	    </@grp>
	    <@ele "FileName",filename,2 />
	    <@grp "AuthRef",2>
	      <@ele "ABrc",abrc,3 />
	      <@ele "ATeller",ateller,3 />
	    </@grp>
	    <@lst "FeeInfo",feeinfo,2;f>
		  <@ele "FeeCode",f.feecode,3 />
		  <@ele "FeeDesc",f.feedesc,3 />
	    </@lst>
	  </@grp>
	  <@grp "Txndict",1>
	     <@ele "EndDate",.globals["02.enddate"],2 />
	  </@grp>
	</@grp>
</@root>