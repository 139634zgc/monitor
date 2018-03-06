(function (root, factory) {if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['exports', 'echarts'], factory);
    } else if (typeof exports === 'object' && typeof exports.nodeName !== 'string') {
        // CommonJS
        factory(exports, require('echarts'));
    } else {
        // Browser globals
        factory({}, root.echarts);
    }
}(this, function (exports, echarts) {
    var log = function (msg) {
        if (typeof console !== 'undefined') {
            console && console.error && console.error(msg);
        }
    };
    if (!echarts) {
        log('ECharts is not Loaded');
        return;
    }

    var colorPalette = [
        '#c23531','#f68207','#44bf4a','#0efbf5',
        '#f30721','#f3de04','#f10eeb','#15cff4','f41393','7709f4','rgba(165,241,31,0.83)'
    ];

    var theme = {

        color: colorPalette,

        title: {
            textStyle: {
                fontWeight: 'normal'
            }
        },

        visualMap: {
            color:['#44bf4a','#f30721']
        },

        toolbox: {
            iconStyle: {
                normal: {
                    borderColor: '#06467c'
                }
            }
        },

        tooltip: {
            backgroundColor: 'rgba(0,0,0,0.6)'
        },

        dataZoom: {
            dataBackgroundColor: '#dedede',
            fillerColor: 'rgba(154,217,247,0.2)',
            handleColor: '#f30721'
        },

        timeline: {
            lineStyle: {
                color: '#f30721'
            },
            controlStyle: {
                normal: {
                    color: '#f30721',
                    borderColor: '#f30721'
                }
            }
        },

        candlestick: {
            itemStyle: {
                normal: {
                    color: '#c23531',
                    color0: '#0efbf5',
                    lineStyle: {
                        width: 1,
                        color: '#c23531',
                        color0: '#0efbf5'
                    }
                }
            }
        },

        graph: {
            color: colorPalette
        },

        map: {
            label: {
                normal: {
                    textStyle: {
                        color: '#c23531'
                    }
                },
                emphasis: {
                    textStyle: {
                        color: '#c23531'
                    }
                }
            },
            itemStyle: {
                normal: {
                    borderColor: '#eee',
                    areaColor: '#ddd'
                },
                emphasis: {
                    areaColor: '#f68207'
                }
            }
        },

        gauge: {
            axisLine: {
                show: true,
                lineStyle: {
                    color: [[0.2, '#0efbf5'],[0.8, '#f30721'],[1, '#c23531']],
                    width: 5
                }
            },
            axisTick: {
                splitNumber: 10,
                length:8,
                lineStyle: {
                    color: 'auto'
                }
            },
            axisLabel: {
                textStyle: {
                    color: 'auto'
                }
            },
            splitLine: {
                length: 12,
                lineStyle: {
                    color: 'auto'
                }
            },
            pointer: {
                length: '90%',
                width: 3,
                color: 'auto'
            },
            title: {
                textStyle: {
                    color: '#333'
                }
            },
            detail: {
                textStyle: {
                    color: 'auto'
                }
            }
        }
    };
    echarts.registerTheme('shine', theme);
}));