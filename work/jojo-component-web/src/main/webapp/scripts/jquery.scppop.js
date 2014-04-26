(function($) {
    $.scppop = function(options) {
        var settings = {
            'popId': 'pop',
            'popClass': '',
            'popContentId': 'popContent',
            'popMessage': '',
            'maskId': 'popMask',
            'maskClass': 'tip-alpha',
            'closeId': '',
            'okId': '',
            'cancelId': '',
            'okCallback': $.noop,
            'cancelCallback': $.noop,
            'dragId': 'popDrag',
            'callback': $.noop
        };
        $.extend(settings, options);
        new scppop(settings);
    };

    function scppop(settings) {
        this._init(settings);
    }

    $.extend(scppop.prototype, {
        _init: function(options) {
            var _this = this;
            this._showLayer(options);
            $('#' + options.popId).show();
            this._showMask(options);
            $('#' + options.maskId).show();
            if (options.closeId != "") {
                var $closeObj = $('#' + options.closeId);
                $closeObj.click(function() {
                    _this._hidePop(options);
                });
            }
            if (options.cancelId != "") {
                var $cancelObj = $('#' + options.cancelId);
                $cancelObj.click(function() {
                    options.cancelCallback();
                    _this._hidePop(options);
                    return false;
                });
            }
            if (options.okId != "") {
                var $okObj = $('#' + options.okId);
                $okObj.click(function() {
                    options.okCallback();
                    _this._hidePop(options);
                    return false;
                });
            }
            $(window).resize(function() {
                _this._showLayer(options);
                _this._showMask(options);
            });
            this._dragAnimate(options);
            options.callback();
        },
        _showLayer: function(options) {
            var $popObj = $('#' + options.popId);
            $popObj.addClass(options.popClass);
            var $popContent = $('#' + options.popContentId);
            $popContent.html(options.popMessage);

            var docEle = document.documentElement || document.body;
            var objLeft = $(window).scrollLeft() + (docEle.clientWidth - $popObj.width()) / 2 + "px";
            var objTop = $(window).scrollTop() + (docEle.clientHeight - $popObj.height()) / 2 + "px";
            $popObj.css({
                'left': objLeft,
                'top': objTop,
                'z-index': 101
            });
        },
        _hidePop: function(options) {
            var $popObj = $('#' + options.popId);
            var $maskObj = $('#' + options.maskId);
            $popObj.hide();
            $maskObj.removeClass(options.maskClass).hide();
        },
        _showMask: function(options) {
            var $maskObj = $('#' + options.maskId);
            $maskObj.addClass(options.maskClass);
            var docEle = document.documentElement || document.body;
            var objWidth = Math.max(docEle.scrollWidth, docEle.offsetWidth, docEle.clientWidth);
            var objHeight = Math.max(docEle.scrollHeight, docEle.offsetHeight, docEle.clientHeight);

            var isIE = $.browser.msie;
            if (isIE) {
                objWidth = objWidth - 5;
                objHeight = objHeight - 5;
            }

            $maskObj.css({
                'width': objWidth,
                'height': objHeight,
                'left': 0,
                'top': 0,
                'position': 'absolute'
            });

            if (isIE && $.browser.version == '6.0') {
                var $iframe = $maskObj.find('iframe');
                if ($iframe.length <= 0) {
                    $maskObj.append('<iframe></iframe>');
                }
                $iframe = $maskObj.find('iframe');
                $iframe.css({
                    'height': objHeight,
                    'width': objWidth
                });
                $iframe.removeClass(options.maskClass).addClass(options.maskClass);
            }
        },
        _dragAnimate: function(options) {
            var $dropObj = $('#' + options.dragId);
            var $popObj = $('#' + options.popId);
            var draging = false;
            var _x = 0, _y = 0;
            var $maskObj = $('#' + options.maskId);
            $dropObj.mousedown(function(e) {
                e = e || window.event;
                if ($.browser.msie ? e.button != '1' : e.button != '0') {
                    return;
                }
                _x = e.pageX - $popObj.offset().left;
                _y = e.pageY - $popObj.offset().top;
                draging = true;

                $(document).mousemove(function(e) {
                    if (!draging) {
                        return;
                    }
                    if (e.pageX - _x > $maskObj.width() - $popObj.width() ||
                    e.pageY - _y > $maskObj.height() - $popObj.height() ||
                    e.pageX - _x < 0 ||
                    e.pageY - _y < 0) {
                        return;
                    }
                    $popObj.css({
                        'left': e.pageX - _x,
                        'top': e.pageY - _y
                    });

                }).mouseup(function() {
                    $(document).unbind('mousemove').unbind('mouseup');
                    draging = false;
                });
            });
        }
    });
})(jQuery);