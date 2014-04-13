<!-- #TopPane -->
<!-- manually attach allowOverflow method to pane -->
<div id="TopPane" class="ui-layout-north" onmouseover="myLayout.allowOverflow('north')" onmouseout="myLayout.resetOverflow(this)">
	This is the north pane, closable, slidable and resizable
	<ul>
		<li>
			<ul>
				<li>one</li>
				<li>two</li>
			</ul>
			Drop-Down <!-- put this below so IE and FF render the same! -->
		</li>
	</ul>
</div>

<!-- #LeftPane -->
<!-- allowOverflow auto-attached by option: west__showOverflowOnHover = true -->
<div id="LeftPane" class="ui-layout-west">
	This is the west pane, closable, slidable and resizable
<button onclick="debugData(myLayout.options.west)">West Options</button>
	<ul>
		<li>
			<ul>
				<li>one</li>
				<li>two</li>
			</ul>
			Pop-Up <!-- put this below so IE and FF render the same! -->
		</li>
	</ul>
	<p><button onclick="myLayout.close('west')">Close Me</button></p>
</div>

<!-- #FooterPane -->
<div id="FooterPane" class="ui-layout-south">
	This is the south pane, closable, slidable and resizable &nbsp;

	<!-- this button has its event added dynamically in document.ready -->
	<button class="south-toggler">Toggle This Pane</button>
</div>

<!-- #RightPane -->
<div id="RightPane" class="ui-layout-east">
	This is the east pane, closable, slidable and resizable

	<!-- attach allowOverflow method to this specific element -->
	<ul onmouseover="myLayout.allowOverflow(this)" onmouseout="myLayout.resetOverflow('east')">
		<li>
			<ul>
				<li>one</li>
				<li>two</li>
			</ul>
			Pop-Up <!-- put this below so IE and FF render the same! -->
		</li>
	</ul>

	<!-- this button has its event added dynamically in document.ready -->
	<p><button id="btnCloseEast">Close Me</button></p>

	<p><select>
		<option value="19">Picklist Test</option>
		<option value="17">tropical storm</option>
		<option value="18">hurricane</option>
		<option value="19">severe thunderstorms</option>
		<option value="20">thunderstorms</option>
		<option value="21">mixed rain and snow</option>
		<option value="22">mixed rain and sleet</option>
		<option value="23">mixed snow and sleet</option>
		<option value="24">freezing drizzle</option>
		<option value="25">drizzle</option>
		<option value="26">freezing rain</option>
		<option value="27">showers</option>
		<option value="28">showers</option>
		<option value="29">snow flurries</option>
		<option value="30">light snow showers</option>
		<option value="31">blowing snow</option>
		<option value="32">snow</option>
		<option value="33">hail</option>
		<option value="34">sleet</option>
		<option value="35">dust</option>
		<option value="36">foggy</option>
		<option value="37">haze</option>
		<option value="38">smoky</option>
		<option value="39">blustery</option>
		<option value="40">windy</option>
		<option value="41">cold</option>
		<option value="42">cloudy</option>
		<option value="43">mostly cloudy (night)</option>
		<option value="44">mostly cloudy (day)</option>
		<option value="45">partly cloudy (night)</option>
		<option value="46">partly cloudy (day)</option>
		<option value="47">clear (night)</option>
		<option value="48">sunny</option>
		<option value="49">fair (night)</option>
		<option value="50">fair (day)</option>
		<option value="51">mixed rain and hail</option>
		<option value="52">hot</option>
		<option value="53">isolated thunderstorms</option>
		<option value="54">scattered thunderstorms</option>
		<option value="55">scattered thunderstorms</option>
		<option value="56">scattered showers</option>
		<option value="57">heavy snow</option>
		<option value="58">scattered snow showers</option>
		<option value="59">heavy snow</option>
		<option value="60">partly cloudy</option>
		<option value="61">thundershowers</option>
		<option value="62">snow showers</option>
		<option value="63">isolated thundershowers</option>
	</select></p>

</div>

<!-- #CenterPane -->
<div id="CenterPane" class="ui-layout-center">
	This CENTER pane auto-sizes to fit the space <i>between</i> the 'border-panes'
	<p>This layout was created with only <b>default options</b> - no customization</p>
	<p>Only the <b>applyDefaultStyles</b> option was enabled for <i>basic</i> formatting</p>
	<p>The Close buttons in East/West panes and the buttons below are examples of <b>custom buttons</b></p>

	<p><a href="http://layout.jquery-dev.net/demos.html"><b>Go to the Demos page</b></a></p>

	<p class="buttons">
		<!-- these buttons have event added dynamically in document.ready -->
		<button id="openAllPanes">Open All Panes</button>
		&nbsp;
		<button id="closeAllPanes">Close All Panes</button>
		&nbsp;
		<button id="toggleAllPanes">Toggle All Panes</button>
	</p>

	<p class="buttons">
		<button onclick="myLayout.toggle('north')">Toggle North Pane</button>
		&nbsp;
		<!-- this button has its event added dynamically in document.ready -->
		<button class="south-toggler">Toggle South Pane</button>
	</p>

	<p class="buttons">
		<button onclick="myLayout.hide('east')">Hide East Pane</button>
		&nbsp;
		<button onclick="myLayout.show('east', false)">Unhide East (Closed)</button>
		&nbsp;
		<button onclick="myLayout.show('east')">Unhide East (Open)</button>
	</p>

	<p class="buttons">
		<button onclick="toggleLiveResizing()">Toggle Live-Resizing (all panes)</button>
		&nbsp;
		<button id="btnToggleState" onclick="toggleStateManagement()">Disable State Cookie</button>
		&nbsp;
		<button id="btnReset" class="hidden" onclick="myLayout.loadState(stateResetSettings, true)">Reset State</button>
	</p>
</div>
