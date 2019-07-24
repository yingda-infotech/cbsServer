<#macro ele name value=name?lower_case?eval level=0>
<#if value??>
<${name}>${value}</${name}>
</#if>
</#macro>
<#macro grp name lvl=0>
<#local nextlvl=lvl+1>
<${name}>
	<#nested nextlvl>
</${name}>
</#macro>
<#macro lst name value level>
<#if value?? && (value?size>0)>
<#local nextlvl=level+1>
<#list value as v>
<${name}>
	<#nested v nextlvl>
</${name}>
</#list>
</#if>
</#macro>
<#macro root>
<?xml version="1.0" encoding="utf-8"?>
<#nested>
</#macro>
<@root>
	<@grp "Transaction",0 >
	  <@grp "Comdict",1>
	    <@grp "PartyRef",1>
	      	<@ele "BankCode",bankcode,2 />
			<@ele "Brc",brc,2 />
			<@ele "BrcName0",brcname0,2 />
			<@ele "BrcType0",brctype0,2 />
			<@ele "BrcState0",brcstate0,2 />
			<@ele "Teller",teller,2 />
			<@ele "TellerName",tellername,2 />
			<@ele "TellerLvl0",tellerlvl0,2 />
			<@ele "TellerType",tellertype,2 />
			<@ele "TellerState0",tellerstate0,2 />
			<@ele "TellerFlag",tellerflag,2 />
	    </@grp>
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
	    <@ele "TellerCode",tellercode,2 />
		<@ele "EndDate",.globals["01.enddate"],2 />
		<@ele "Sex",.globals["01.sex"],2 />
		<@ele "IdType",.globals["01.idtype"],2 />
		<@ele "NewPreChar",.globals["01.newprechar"],2 />
		<@ele "OldPreChar",.globals["01.oldprechar"],2 />
		<@ele "Phone",.globals["01.phone"],2 />
		<@ele "Political",.globals["01.political"],2 />
		<@ele "Flag4",.globals["01.flag4"],2 />
		<@ele "IdNo",.globals["01.idno"],2 />
		<@ele "DepartMent",.globals["01.department"],2 />
		<@ele "BoxNo",.globals["01.boxno"],2 />
		<@ele "BrcCode",.globals["01.brccode"],2 />
		<@ele "BrcName",.globals["01.brcname"],2 />
		<@ele "BrcType",.globals["01.brctype"],2 />
		<@ele "CDFlag",.globals["01.cdflag"],2 />
		<@ele "CardFlag",.globals["01.cardflag"],2 />
		<@ele "CardSeqNo",.globals["01.cardseqno"],2 />
		<@ele "CertiNo",.globals["01.certino"],2 />
		<@ele "ChkFlag",.globals["01.chkflag"],2 />
		<@ele "CustMngNo",.globals["01.custmngno"],2 />
		<@ele "CustomName",.globals["01.customname"],2 />
		<@ele "TellerAttr",.globals["01.tellerattr"],2 />
		<@ele "TellerType",.globals["01.tellertype"],2 />
		<@ele "Address",.globals["01.address"],2 />
		<@ele "BeginDate",.globals["01.begindate"],2 />
	  </@grp>
	</@grp>
</@root>