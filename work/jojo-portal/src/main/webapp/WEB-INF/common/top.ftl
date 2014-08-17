<!--common javascript-->
<script type="text/javascript" src="${rc.contextPath}/scripts/jquery.js"></script>
<script type="text/javascript" src="${rc.contextPath}/scripts/jquery.JPlaceholder.js"></script>
<script type="text/javascript" src="${rc.contextPath}/scripts/date/WdatePicker.js"></script>
<script type="text/javascript" src="${rc.contextPath}/scripts/validate_form.js"></script>
<div id="top">
	<div class="grid">
		<dl class="fun-nav">
			<#if form.hasAuthentication("${rc.contextPath}/punuser/list") || form.hasAuthentication("${rc.contextPath}/punuser/personal/edit") || form.hasAuthentication("${rc.contextPath}/punuser/password/edit") || form.hasAuthentication("${rc.contextPath}/punuser/role/list")>
				<dt class="menu-dt">
					<a>系统管理 <b></b></a>
					<ul>
						<#if form.hasAuthentication("${rc.contextPath}/punuser/list")>
							<li>
								<a href="${rc.contextPath}/punuser/list">用户列表</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/punuser/personal/edit")>
							<li>
								<a href="${rc.contextPath}/punuser/personal/edit">个人信息修改</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/punuser/password/edit")>
							<li>
								<a href="${rc.contextPath}/punuser/password/edit">密码修改</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/punuser/role/list")>
							<li>
								<a href="${rc.contextPath}/punuser/role/list">角色列表</a>
							</li>
						</#if>
						<#if form.hasAuthentication("${rc.contextPath}/punparam/listSysParam")>
							<li>
								<a href="${rc.contextPath}/punparam/listSysParam">开关参数列表</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/punparam/switch/intfCtrl")>
							<li>
								<a href="${rc.contextPath}/punparam/switch/intfCtrl">风控模块控制</a>
							</li>
						</#if>
					</ul>
				</dt>
			</#if>

			<#if form.hasAuthentication("${rc.contextPath}/rule/list")
			|| form.hasAuthentication("${rc.contextPath}/rule/pos/add")
			|| form.hasAuthentication("${rc.contextPath}/rule/epos/add")
			||form.hasAuthentication("${rc.contextPath}/ruledata/listRuleTag")
			||form.hasAuthentication("${rc.contextPath}/ruledata/addRuleTag")
			||form.hasAuthentication("${rc.contextPath}/ruledata/epos/addRuleTag")
			|| form.hasAuthentication("${rc.contextPath}/rule/listBatchRule")
			|| form.hasAuthentication("${rc.contextPath}/rule/gam/addBatchRule")
			|| form.hasAuthentication("${rc.contextPath}/ruledata/listGamRuleTag")
			|| form.hasAuthentication("${rc.contextPath}/ruledata/toAddGamBatchRuleTag") >
				<dt class="menu-dt">
					<a>风控规则 <b></b></a>
					<ul>
						<#if form.hasAuthentication("${rc.contextPath}/rule/list")
						|| form.hasAuthentication("${rc.contextPath}/rule/pos/add")
						|| form.hasAuthentication("${rc.contextPath}/rule/epos/add")>
							<li>
								<a href="${rc.contextPath}/rule/list">准实时规则</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/rule/listBatchRule")
						|| form.hasAuthentication("${rc.contextPath}/rule/gam/addBatchRule")>
							<li>
								<a href="${rc.contextPath}/rule/listBatchRule">批量规则</a>
							</li>
						</#if>


						<#if form.hasAuthentication("${rc.contextPath}/ruledata/listRuleTag")
						|| form.hasAuthentication("${rc.contextPath}/ruledata/addRuleTag")
						|| form.hasAuthentication("${rc.contextPath}/ruledata/epos/addRuleTag")>
							<li>
								<a href="${rc.contextPath}/ruledata/listRuleTag">准实时规则标签</a>
							</li>
						</#if>


						<#if form.hasAuthentication("${rc.contextPath}/ruledata/listGamRuleTag")
						|| form.hasAuthentication("${rc.contextPath}/ruledata/toAddGamBatchRuleTag")>
							<li>
								<a href="${rc.contextPath}/ruledata/listGamRuleTag">批量规则标签</a>
							</li>
						</#if>

					</ul>
				</dt>
			</#if>


			<#if form.hasAuthentication("${rc.contextPath}/punbrl/confiscatecard/list") || form.hasAuthentication("${rc.contextPath}/punbrl/highriskcard/list") || form.hasAuthentication("${rc.contextPath}/punbrl/riskcardbin/listriskcardbin") ||form.hasAuthentication("${rc.contextPath}/punbrl/mcc/list") >
				<dt class="menu-dt">
					<a>风险信息库 <b></b></a>
					<ul>
						<#if form.hasAuthentication("${rc.contextPath}/punbrl/confiscatecard/list")>
							<li>
								<a href="${rc.contextPath}/punbrl/confiscatecard/list">没收卡代码</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/punbrl/highriskcard/list")>
							<li>
								<a href="${rc.contextPath}/punbrl/highriskcard/list">高风险代码</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/punbrl/riskcardbin/listriskcardbin")>
							<li>
								<a href="${rc.contextPath}/punbrl/riskcardbin/listriskcardbin">高风险卡BIN</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/punbrl/mcc/list")>
							<li>
								<a href="${rc.contextPath}/punbrl/mcc/list">低费率MCC</a>
							</li>
						</#if>
					</ul>
				</dt>
			</#if>

			<#if form.hasAuthentication("${rc.contextPath}/bwlist/merchant/list")
			    || form.hasAuthentication("${rc.contextPath}/bwlist/pnrdevid/list")
			    || form.hasAuthentication("${rc.contextPath}/bwlist/cardbin/list")
			    || form.hasAuthentication("${rc.contextPath}/bwlist/cardno/list")
			    || form.hasAuthentication("${rc.contextPath}/bwlist/phoneno/list") >
				<dt class="menu-dt">
					<a>黑白名单 <b></b></a>
					<ul>
						<#if form.hasAuthentication("${rc.contextPath}/bwlist/merchant/list")>
							<li>
								<a href="${rc.contextPath}/bwlist/merchant/list">用户黑白名单</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/bwlist/pnrdevid/list")>
							<li>
								<a href="${rc.contextPath}/bwlist/pnrdevid/list">终端黑白名单</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/bwlist/cardbin/list")>
							<li>
								<a href="${rc.contextPath}/bwlist/cardbin/list">卡BIN黑白名单</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/bwlist/cardno/list")>
							<li>
								<a href="${rc.contextPath}/bwlist/cardno/list">卡号黑白名单</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/bwlist/phoneno/list")>
							<li>
								<a href="${rc.contextPath}/bwlist/phoneno/list">电话黑白名单</a>
							</li>
						</#if>
					</ul>
				</dt>
			</#if>

			<#if form.hasAuthentication("${rc.contextPath}/limit/merchant/list") || form.hasAuthentication("${rc.contextPath}/limit/mcc/list") || form.hasAuthentication("${rc.contextPath}/limit/cardbin/list") ||form.hasAuthentication("${rc.contextPath}/limit/cardno/list") >
				<dt class="menu-dt">
					<a>限额限次 <b></b></a>
					<ul>
						<#if form.hasAuthentication("${rc.contextPath}/limit/merchant/list")>
							<li>
								<a href="${rc.contextPath}/limit/merchant/list">商户限额限次</a>
							</li>
						</#if>
						<#if form.hasAuthentication("${rc.contextPath}/limit/mcc/list")>
							<li>
								<a href="${rc.contextPath}/limit/mcc/list">MCC限额限次</a>
							</li>
						</#if>
						<#if form.hasAuthentication("${rc.contextPath}/limit/cardbin/list")>
							<li>
								<a href="${rc.contextPath}/limit/cardbin/list">卡BIN限额限次</a>
							</li>
						</#if>
						<#if form.hasAuthentication("${rc.contextPath}/limit/cardno/list")>
							<li>
								<a href="${rc.contextPath}/limit/cardno/list">卡号限额限次</a>
							</li>
						</#if>
					</ul>
				</dt>
			</#if>

			<!--
			<#if form.hasAuthentication("${rc.contextPath}/pundetail/list")>
				<dt style="background: none;">
					<a href="${rc.contextPath}/pundetail/list">处罚记录</a>
				</dt>
			</#if>
			-->

			<#if form.hasAuthentication("${rc.contextPath}/interceptlog/list")
			     || form.hasAuthentication("${rc.contextPath}/interceptlog/epos/list")
			     || form.hasAuthentication("${rc.contextPath}/interceptlog/posmini/list") >
				<dt class="menu-dt">
					<a>拦截记录 <b></b></a>
					<ul>
						<#if form.hasAuthentication("${rc.contextPath}/interceptlog/list")>
							<li>
								<a href="${rc.contextPath}/interceptlog/list">POS支付拦截</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/interceptlog/epos/list")>
							<li>
								<a href="${rc.contextPath}/interceptlog/epos/list">电话支付拦截</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/interceptlog/posmini/list")>
                            <li>
                                <a href="${rc.contextPath}/interceptlog/posmini/list">POSMINI拦截</a>
                            </li>
                        </#if>
					</ul>
				</dt>
			</#if>

			<#if form.hasAuthentication("${rc.contextPath}/ruleresult/list")
			|| form.hasAuthentication("${rc.contextPath}/trade/list")
		  	|| form.hasAuthentication("${rc.contextPath}/credit/list") >
				<dt class="menu-dt">
					<a>风险记录 <b></b></a>
					<ul>
						<#if form.hasAuthentication("${rc.contextPath}/ruleresult/list")>
							<li>
								<a href="${rc.contextPath}/ruleresult/list">风险记录列表</a>
							</li>
						</#if>
						<!--
						<#if form.hasAuthentication("${rc.contextPath}/risk/trade/list")>
                            <li>
                                <a href="${rc.contextPath}/risk/trade/list">预警交易查询</a>
                            </li>
                        </#if>
						-->
						<#if form.hasAuthentication("${rc.contextPath}/credit/list")>
							<li>
								<a href="${rc.contextPath}/credit/list">预警查询</a>
							</li>
						</#if>

						<#if form.hasAuthentication("${rc.contextPath}/trade/list")>
							<li>
								<a href="${rc.contextPath}/trade/list">电话支付交易列表</a>
							</li>
						</#if>
					    <#if form.hasAuthentication("${rc.contextPath}/trade/sw/list")>
                            <li>
                                <a href="${rc.contextPath}/trade/sw/list">超级白名单交易列表</a>
                            </li>
                        </#if>
					</ul>
				</dt>
			</#if>

			<#if form.hasAuthentication("${rc.contextPath}/punlog/punLogList")
				||form.hasAuthentication("${rc.contextPath}/puncenter/punact/list")
				|| form.hasAuthentication("${rc.contextPath}/puncenter/punact/add") >
				<dt class="menu-dt">
					<a>处罚中心 <b></b></a>
					<ul>
						<li>
							<a href="${rc.contextPath}/punlog/punLogList">处罚日志查询</a>
						</li>
						<#if form.hasAuthentication("${rc.contextPath}/puncenter/punact/list")
						|| form.hasAuthentication("${rc.contextPath}/puncenter/punact/add")>
							<li>
								<a href="${rc.contextPath}/puncenter/punact/list">行动维护</a>
							</li>
						</#if>
					</ul>
				</dt>
	        </#if>

			<#if form.hasAuthentication("${rc.contextPath}/neginfo/uploadEntry")
				|| form.hasAuthentication("${rc.contextPath}/neginfo/queryNegInfo")>
				<dt class="menu-dt">
					<a>外部平台 <b></b></a>
					<ul>
						<#if form.hasAuthentication("${rc.contextPath}/neginfo/uploadEntry")>
							<li>
								<a href="${rc.contextPath}/neginfo/uploadEntry">负面信息上传</a>
							</li>
						</#if>
						<#if form.hasAuthentication("${rc.contextPath}/neginfo/queryNegInfo")>
							<li>
								<a href="${rc.contextPath}/neginfo/queryNegInfo">负面信息查询</a>
							</li>
						</#if>
					</ul>
				</dt>
			</#if>

			<#if form.hasAuthentication("${rc.contextPath}/lbs/result/list")
				|| form.hasAuthentication("${rc.contextPath}/lbs/warn/list")>
				<dt class="menu-dt">
					<a>LBS查询 <b></b></a>
					<ul>
						<#if form.hasAuthentication("${rc.contextPath}/lbs/result/list")>
							<li>
								<a href="${rc.contextPath}/lbs/result/list">LBS拦截记录查询</a>
							</li>
						</#if>
						<#if form.hasAuthentication("${rc.contextPath}/lbs/warn/list")>
							<li>
								<a href="${rc.contextPath}/lbs/warn/list">LBS异常日志查询</a>
							</li>
						</#if>
					</ul>
				</dt>
			</#if>
			<#if form.hasAuthentication("${rc.contextPath}/creditRaterepResult/list")>
				<dt class="menu-dt">
					<a>报表查询 <b></b></a>
					<ul>
						<#if form.hasAuthentication("${rc.contextPath}/creditRaterepResult/list")>
							<li>
								<a href="${rc.contextPath}/creditRaterepResult/list">信用支付使用率报表</a>
							</li>
						</#if>
					</ul>
				</dt>
			</#if>
		</dl>

		<ul class="user-nav">
			<li>
			<#if form?exists>
			<#if form.logon>
			您好，<span class="h3" style="padding-right: 6px; background: url(${rc.contextPath}/images/sep-line.png) right 50% no-repeat;">${form.contextUserName}</span> <a href="${rc.contextPath}/logout">退出</a>
			</#if>
			<#else>
			您好，<span class="h3">${userName}</span>
			</#if>
			</li>
		</ul>
	</div>
</div>
<div id="header"><a href="${rc.contextPath}/index"><img title="物料管理系统" class="logo" src="${rc.contextPath}/images/logo.jpg" alt="物料管理系统" border="0"></a></div>