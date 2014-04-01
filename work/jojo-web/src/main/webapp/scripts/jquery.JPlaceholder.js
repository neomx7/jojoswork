/*
 * jQuery placeholder, fix for IE6,7,8,9
 * @author xiaoyu.wang_C
 * @since 2014.02.07
 */
var JPlaceHolder = {
	//检测
	_check : function(){
		return 'placeholder' in document.createElement('input');
	},
	//初始化
	init : function(){
		if(!this._check()){
			this.fix();
		}
	},
	//修复
	fix : function(){
		jQuery(':input[placeholder]').each(function(index, element) {
            var self = $(this), txt = self.attr('placeholder');
			self.wrap($('<div></div>').css({position:'relative', zoom:'1', border:'none', background:'none', padding:'none', margin:'none', display:'inline'}));
			var pos = self.position(), h = self.outerHeight(true), paddingleft = self.css('padding-left');
			var holder = $('<span></span>').text(txt).css({position:'absolute', left:pos.left+6, top:pos.top+6, height:h, lienHeight:h, paddingLeft:paddingleft, color:'#aaa'}).appendTo(self.parent());
			if(''!=self.val()){
				holder.hide();
			}
			self.focusin(function(e) {
                holder.hide();
            }).focusout(function(e) {
                if(!self.val()){
					holder.show();
				}
            });
			holder.click(function(e) {
                holder.hide();
				self.focus();
            });
        });
	}
};
//执行
jQuery(function(){
	JPlaceHolder.init();	
});
