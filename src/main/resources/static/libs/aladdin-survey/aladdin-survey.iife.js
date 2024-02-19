var AladdinSurvey=function(){"use strict";var An=Object.defineProperty;var vn=(x,E,V)=>E in x?An(x,E,{enumerable:!0,configurable:!0,writable:!0,value:V}):x[E]=V;var $e=(x,E,V)=>(vn(x,typeof E!="symbol"?E+"":E,V),V);function x(){}function E(t,e){for(const n in e)t[n]=e[n];return t}function V(t){return t()}function ye(){return Object.create(null)}function H(t){t.forEach(V)}function Be(t){return typeof t=="function"}function I(t,e){return t!=t?e==e:t!==e||t&&typeof t=="object"||typeof t=="function"}function ze(t){return Object.keys(t).length===0}function pe(t,e,n,l){if(t){const u=Se(t,e,n,l);return t[0](u)}}function Se(t,e,n,l){return t[1]&&l?E(n.ctx.slice(),t[1](l(e))):n.ctx}function de(t,e,n,l){if(t[2]&&l){const u=t[2](l(n));if(e.dirty===void 0)return u;if(typeof u=="object"){const s=[],i=Math.max(e.dirty.length,u.length);for(let f=0;f<i;f+=1)s[f]=e.dirty[f]|u[f];return s}return e.dirty|u}return e.dirty}function ge(t,e,n,l,u,s){if(u){const i=Se(e,n,l,s);t.p(i,u)}}function me(t){if(t.ctx.length>32){const e=[],n=t.ctx.length/32;for(let l=0;l<n;l++)e[l]=-1;return e}return-1}function U(t){const e={};for(const n in t)n[0]!=="$"&&(e[n]=t[n]);return e}function Ce(t){return t??""}function b(t,e){t.appendChild(e)}function $(t,e,n){t.insertBefore(e,n||null)}function w(t){t.parentNode&&t.parentNode.removeChild(t)}function W(t,e){for(let n=0;n<t.length;n+=1)t[n]&&t[n].d(e)}function S(t){return document.createElement(t)}function G(t){return document.createElementNS("http://www.w3.org/2000/svg",t)}function F(t){return document.createTextNode(t)}function O(){return F(" ")}function ke(){return F("")}function R(t,e,n,l){return t.addEventListener(e,n,l),()=>t.removeEventListener(e,n,l)}function _(t,e,n){n==null?t.removeAttribute(e):t.getAttribute(e)!==n&&t.setAttribute(e,n)}function Ne(t,e,n){t.setAttributeNS("http://www.w3.org/1999/xlink",e,n)}function Je(t){return Array.from(t.childNodes)}function re(t,e){e=""+e,t.data!==e&&(t.data=e)}function D(t,e){t.value=e??""}function qe(t,e,n){for(let l=0;l<t.options.length;l+=1){const u=t.options[l];if(u.__value===e){u.selected=!0;return}}(!n||e!==void 0)&&(t.selectedIndex=-1)}function Ke(t){const e=t.querySelector(":checked");return e&&e.__value}function He(t,e,{bubbles:n=!1,cancelable:l=!1}={}){return new CustomEvent(t,{detail:e,bubbles:n,cancelable:l})}let ne;function le(t){ne=t}function We(){if(!ne)throw new Error("Function called outside component initialization");return ne}function xe(){const t=We();return(e,n,{cancelable:l=!1}={})=>{const u=t.$$.callbacks[e];if(u){const s=He(e,n,{cancelable:l});return u.slice().forEach(i=>{i.call(t,s)}),!s.defaultPrevented}return!0}}function ie(t,e){const n=t.$$.callbacks[e.type];n&&n.slice().forEach(l=>l.call(this,e))}const Z=[],j=[];let X=[];const he=[],Ze=Promise.resolve();let Ae=!1;function Xe(){Ae||(Ae=!0,Ze.then(Ee))}function fe(t){X.push(t)}function P(t){he.push(t)}const ve=new Set;let ee=0;function Ee(){if(ee!==0)return;const t=ne;do{try{for(;ee<Z.length;){const e=Z[ee];ee++,le(e),et(e.$$)}}catch(e){throw Z.length=0,ee=0,e}for(le(null),Z.length=0,ee=0;j.length;)j.pop()();for(let e=0;e<X.length;e+=1){const n=X[e];ve.has(n)||(ve.add(n),n())}X.length=0}while(Z.length);for(;he.length;)he.pop()();Ae=!1,ve.clear(),le(t)}function et(t){if(t.fragment!==null){t.update(),H(t.before_update);const e=t.dirty;t.dirty=[-1],t.fragment&&t.fragment.p(t.ctx,e),t.after_update.forEach(fe)}}function tt(t){const e=[],n=[];X.forEach(l=>t.indexOf(l)===-1?e.push(l):n.push(l)),n.forEach(l=>l()),X=e}const ce=new Set;let z;function ue(){z={r:0,c:[],p:z}}function se(){z.r||H(z.c),z=z.p}function A(t,e){t&&t.i&&(ce.delete(t),t.i(e))}function y(t,e,n,l){if(t&&t.o){if(ce.has(t))return;ce.add(t),z.c.push(()=>{ce.delete(t),l&&(n&&t.d(1),l())}),t.o(e)}else l&&l()}function Q(t){return(t==null?void 0:t.length)!==void 0?t:Array.from(t)}function T(t,e,n){const l=t.$$.props[e];l!==void 0&&(t.$$.bound[l]=n,n(t.$$.ctx[l]))}function N(t){t&&t.c()}function C(t,e,n){const{fragment:l,after_update:u}=t.$$;l&&l.m(e,n),fe(()=>{const s=t.$$.on_mount.map(V).filter(Be);t.$$.on_destroy?t.$$.on_destroy.push(...s):H(s),t.$$.on_mount=[]}),u.forEach(fe)}function k(t,e){const n=t.$$;n.fragment!==null&&(tt(n.after_update),H(n.on_destroy),n.fragment&&n.fragment.d(e),n.on_destroy=n.fragment=null,n.ctx=[])}function nt(t,e){t.$$.dirty[0]===-1&&(Z.push(t),Xe(),t.$$.dirty.fill(0)),t.$$.dirty[e/31|0]|=1<<e%31}function L(t,e,n,l,u,s,i=null,f=[-1]){const o=ne;le(t);const c=t.$$={fragment:null,ctx:[],props:s,update:x,not_equal:u,bound:ye(),on_mount:[],on_destroy:[],on_disconnect:[],before_update:[],after_update:[],context:new Map(e.context||(o?o.$$.context:[])),callbacks:ye(),dirty:f,skip_bound:!1,root:e.target||o.$$.root};i&&i(c.root);let a=!1;if(c.ctx=n?n(t,e.props||{},(r,p,...g)=>{const d=g.length?g[0]:p;return c.ctx&&u(c.ctx[r],c.ctx[r]=d)&&(!c.skip_bound&&c.bound[r]&&c.bound[r](d),a&&nt(t,r)),p}):[],c.update(),a=!0,H(c.before_update),c.fragment=l?l(c.ctx):!1,e.target){if(e.hydrate){const r=Je(e.target);c.fragment&&c.fragment.l(r),r.forEach(w)}else c.fragment&&c.fragment.c();e.intro&&A(t.$$.fragment),C(t,e.target,e.anchor),Ee()}le(o)}class M{constructor(){$e(this,"$$");$e(this,"$$set")}$destroy(){k(this,1),this.$destroy=x}$on(e,n){if(!Be(n))return x;const l=this.$$.callbacks[e]||(this.$$.callbacks[e]=[]);return l.push(n),()=>{const u=l.indexOf(n);u!==-1&&l.splice(u,1)}}$set(e){this.$$set&&!ze(e)&&(this.$$.skip_bound=!0,this.$$set(e),this.$$.skip_bound=!1)}}const lt="4";typeof window<"u"&&(window.__svelte||(window.__svelte={v:new Set})).v.add(lt);function it(t){return t&&t.__esModule&&Object.prototype.hasOwnProperty.call(t,"default")?t.default:t}var Ge={exports:{}};/*!
	Copyright (c) 2018 Jed Watson.
	Licensed under the MIT License (MIT), see
	http://jedwatson.github.io/classnames
*/(function(t){(function(){var e={}.hasOwnProperty;function n(){for(var s="",i=0;i<arguments.length;i++){var f=arguments[i];f&&(s=u(s,l(f)))}return s}function l(s){if(typeof s=="string"||typeof s=="number")return s;if(typeof s!="object")return"";if(Array.isArray(s))return n.apply(null,s);if(s.toString!==Object.prototype.toString&&!s.toString.toString().includes("[native code]"))return s.toString();var i="";for(var f in s)e.call(s,f)&&s[f]&&(i=u(i,f));return i}function u(s,i){return i?s?s+" "+i:s+i:s}t.exports?(n.default=n,t.exports=n):window.classNames=n})()})(Ge);var ut=Ge.exports;const J=it(ut);function st(t){let e;return{c(){e=S("span"),_(e,"class",t[0])},m(n,l){$(n,e,l)},p(n,[l]){l&1&&_(e,"class",n[0])},i:x,o:x,d(n){n&&w(e)}}}const ot="w-5 h-5 border border-solid border-2 border-gray-300 rounded-full";function rt(t,e,n){let l;return t.$$set=u=>{n(1,e=E(E({},e),U(u)))},t.$$.update=()=>{n(0,l=J(ot,e.className??e.className))},e=U(e),[l]}class be extends M{constructor(e){super(),L(this,e,rt,st,I,{})}}function ft(t){let e,n,l,u,s;const i=t[3].default,f=pe(i,t,t[2],null);return{c(){e=S("select"),f&&f.c(),_(e,"class",n=Ce(t[1])+" svelte-f6x3ur"),t[0]===void 0&&fe(()=>t[6].call(e))},m(o,c){$(o,e,c),f&&f.m(e,null),qe(e,t[0],!0),l=!0,u||(s=[R(e,"pointerup",t[4]),R(e,"change",t[5]),R(e,"change",t[6])],u=!0)},p(o,[c]){f&&f.p&&(!l||c&4)&&ge(f,i,o,o[2],l?de(i,o[2],c,null):me(o[2]),null),(!l||c&2&&n!==(n=Ce(o[1])+" svelte-f6x3ur"))&&_(e,"class",n),c&1&&qe(e,o[0])},i(o){l||(A(f,o),l=!0)},o(o){y(f,o),l=!1},d(o){o&&w(e),f&&f.d(o),u=!1,H(s)}}}const ct="border p-2 pl-4 pr-8 rounded-s appearance-none bg-no-repeat";function at(t,e,n){let l,{$$slots:u={},$$scope:s}=e,{value:i=""}=e;function f(a){ie.call(this,t,a)}function o(a){ie.call(this,t,a)}function c(){i=Ke(this),n(0,i)}return t.$$set=a=>{n(7,e=E(E({},e),U(a))),"value"in a&&n(0,i=a.value),"$$scope"in a&&n(2,s=a.$$scope)},t.$$.update=()=>{n(1,l=J(ct,e.className??e.className))},e=U(e),[i,l,s,u,f,o,c]}class we extends M{constructor(e){super(),L(this,e,at,ft,I,{value:0})}}function _t(t){let e,n,l;return{c(){e=S("input"),_(e,"type","text"),_(e,"placeholder",t[1]),_(e,"class",t[2])},m(u,s){$(u,e,s),D(e,t[0]),n||(l=R(e,"input",t[4]),n=!0)},p(u,[s]){s&2&&_(e,"placeholder",u[1]),s&4&&_(e,"class",u[2]),s&1&&e.value!==u[0]&&D(e,u[0])},i:x,o:x,d(u){u&&w(e),n=!1,l()}}}const pt="p-2 focus:outline-none hover:border-b-2 focus:border-b-2 ";function dt(t,e,n){let l,{placeholder:u="입력 하세요..."}=e,{value:s}=e,{color:i="violet"}=e;const f={gray:"border-gray-200 focus:border-gray-400",violet:"border-violet-200 focus:border-violet-400"};function o(){s=this.value,n(0,s)}return t.$$set=c=>{n(6,e=E(E({},e),U(c))),"placeholder"in c&&n(1,u=c.placeholder),"value"in c&&n(0,s=c.value),"color"in c&&n(3,i=c.color)},t.$$.update=()=>{n(2,l=J(pt,f[i],e.className??e.className))},e=U(e),[s,u,l,i,o]}class K extends M{constructor(e){super(),L(this,e,dt,_t,I,{placeholder:1,value:0,color:3})}}function Oe(t,e,n){const l=t.slice();return l[6]=e[n],l[7]=e,l[8]=n,l}function Ye(t,e,n){const l=t.slice();return l[9]=e[n],l}function Ie(t){let e,n=t[9].id+"",l,u,s=t[9].title+"",i,f,o;return{c(){e=S("option"),l=F(n),u=F("섹션("),i=F(s),f=F(") 으로 이동"),e.__value=o=t[9].id,D(e,e.__value)},m(c,a){$(c,e,a),b(e,l),b(e,u),b(e,i),b(e,f)},p(c,a){a&2&&n!==(n=c[9].id+"")&&re(l,n),a&2&&s!==(s=c[9].title+"")&&re(i,s),a&2&&o!==(o=c[9].id)&&(e.__value=o,D(e,e.__value))},d(c){c&&w(e)}}}function gt(t){let e,n,l,u=Q(t[1]),s=[];for(let i=0;i<u.length;i+=1)s[i]=Ie(Ye(t,u,i));return{c(){e=S("option"),e.textContent="다음 섹션으로 진행",n=O();for(let i=0;i<s.length;i+=1)s[i].c();l=ke(),e.__value="",D(e,e.__value)},m(i,f){$(i,e,f),$(i,n,f);for(let o=0;o<s.length;o+=1)s[o]&&s[o].m(i,f);$(i,l,f)},p(i,f){if(f&2){u=Q(i[1]);let o;for(o=0;o<u.length;o+=1){const c=Ye(i,u,o);s[o]?s[o].p(c,f):(s[o]=Ie(c),s[o].c(),s[o].m(l.parentNode,l))}for(;o<s.length;o+=1)s[o].d(1);s.length=u.length}},d(i){i&&(w(e),w(n),w(l)),W(s,i)}}}function je(t){let e,n,l,u,s,i,f,o,c,a,r;l=new be({});function p(h){t[3](h,t[6])}let g={className:"w-full border-b-2 focus:border-b-2",color:"gray",type:"text"};t[6].label!==void 0&&(g.value=t[6].label),s=new K({props:g}),j.push(()=>T(s,"value",p));function d(h){t[4](h,t[6])}function m(...h){return t[5](t[6],...h)}let B={$$slots:{default:[gt]},$$scope:{ctx:t}};return t[6].nextSection!==void 0&&(B.value=t[6].nextSection),o=new we({props:B}),j.push(()=>T(o,"value",d)),o.$on("change",m),{c(){e=S("div"),n=S("div"),N(l.$$.fragment),u=O(),N(s.$$.fragment),f=O(),N(o.$$.fragment),a=O(),_(n,"class","flex flex-row items-center gap-2 flex-1"),_(e,"class","flex flex-row bg-white items-end gap-2")},m(h,Y){$(h,e,Y),b(e,n),C(l,n,null),b(n,u),C(s,n,null),b(e,f),C(o,e,null),b(e,a),r=!0},p(h,Y){t=h;const v={};!i&&Y&1&&(i=!0,v.value=t[6].label,P(()=>i=!1)),s.$set(v);const q={};Y&4098&&(q.$$scope={dirty:Y,ctx:t}),!c&&Y&1&&(c=!0,q.value=t[6].nextSection,P(()=>c=!1)),o.$set(q)},i(h){r||(A(l.$$.fragment,h),A(s.$$.fragment,h),A(o.$$.fragment,h),r=!0)},o(h){y(l.$$.fragment,h),y(s.$$.fragment,h),y(o.$$.fragment,h),r=!1},d(h){h&&w(e),k(l),k(s),k(o)}}}function mt(t){let e,n,l=Q(t[0]),u=[];for(let i=0;i<l.length;i+=1)u[i]=je(Oe(t,l,i));const s=i=>y(u[i],1,1,()=>{u[i]=null});return{c(){e=S("div");for(let i=0;i<u.length;i+=1)u[i].c();_(e,"class","flex flex-col gap-1")},m(i,f){$(i,e,f);for(let o=0;o<u.length;o+=1)u[o]&&u[o].m(e,null);n=!0},p(i,[f]){if(f&3){l=Q(i[0]);let o;for(o=0;o<l.length;o+=1){const c=Oe(i,l,o);u[o]?(u[o].p(c,f),A(u[o],1)):(u[o]=je(c),u[o].c(),A(u[o],1),u[o].m(e,null))}for(ue(),o=l.length;o<u.length;o+=1)s(o);se()}},i(i){if(!n){for(let f=0;f<l.length;f+=1)A(u[f]);n=!0}},o(i){u=u.filter(Boolean);for(let f=0;f<u.length;f+=1)y(u[f]);n=!1},d(i){i&&w(e),W(u,i)}}}function ht(t,e){const n=t.target;e.nextSection=n.value}function At(t,e,n){let{options:l}=e,{survey:u}=e,s=u.sections;function i(c,a){t.$$.not_equal(a.label,c)&&(a.label=c,n(0,l))}function f(c,a){t.$$.not_equal(a.nextSection,c)&&(a.nextSection=c,n(0,l))}const o=(c,a)=>ht(a,c);return t.$$set=c=>{"options"in c&&n(0,l=c.options),"survey"in c&&n(2,u=c.survey)},t.$$.update=()=>{t.$$.dirty&4&&n(1,s=u.sections)},[l,s,u,i,f,o]}class Le extends M{constructor(e){super(),L(this,e,At,mt,I,{options:0,survey:2})}}function vt(t){let e,n;const l=t[2].default,u=pe(l,t,t[1],null);return{c(){e=S("div"),u&&u.c(),_(e,"class",t[0])},m(s,i){$(s,e,i),u&&u.m(e,null),n=!0},p(s,[i]){u&&u.p&&(!n||i&2)&&ge(u,l,s,s[1],n?de(l,s[1],i,null):me(s[1]),null),(!n||i&1)&&_(e,"class",s[0])},i(s){n||(A(u,s),n=!0)},o(s){y(u,s),n=!1},d(s){s&&w(e),u&&u.d(s)}}}const bt="p-2 pt-4 border-b border-dotted border-violet-400 text-gray-400 ";function wt(t,e,n){let l,{$$slots:u={},$$scope:s}=e;return t.$$set=i=>{n(3,e=E(E({},e),U(i))),"$$scope"in i&&n(1,s=i.$$scope)},t.$$.update=()=>{n(0,l=J(bt,e.short?"w-64":"w-96"))},e=U(e),[l,s,u]}class Me extends M{constructor(e){super(),L(this,e,wt,vt,I,{})}}function Pe(t,e,n){const l=t.slice();return l[7]=e[n],l[8]=e,l[9]=n,l}function Te(t,e,n){const l=t.slice();return l[10]=e[n],l}function Ue(t){let e,n=t[10].id+"",l,u,s=t[10].title+"",i,f,o;return{c(){e=S("option"),l=F(n),u=F("섹션("),i=F(s),f=F(") 으로 이동"),e.__value=o=t[10].id,D(e,e.__value)},m(c,a){$(c,e,a),b(e,l),b(e,u),b(e,i),b(e,f)},p(c,a){a&2&&n!==(n=c[10].id+"")&&re(l,n),a&2&&s!==(s=c[10].title+"")&&re(i,s),a&2&&o!==(o=c[10].id)&&(e.__value=o,D(e,e.__value))},d(c){c&&w(e)}}}function $t(t){let e,n,l,u=Q(t[1]),s=[];for(let i=0;i<u.length;i+=1)s[i]=Ue(Te(t,u,i));return{c(){e=S("option"),e.textContent="다음 섹션으로 진행",n=O();for(let i=0;i<s.length;i+=1)s[i].c();l=ke(),e.__value="",D(e,e.__value)},m(i,f){$(i,e,f),$(i,n,f);for(let o=0;o<s.length;o+=1)s[o]&&s[o].m(i,f);$(i,l,f)},p(i,f){if(f&2){u=Q(i[1]);let o;for(o=0;o<u.length;o+=1){const c=Te(i,u,o);s[o]?s[o].p(c,f):(s[o]=Ue(c),s[o].c(),s[o].m(l.parentNode,l))}for(;o<s.length;o+=1)s[o].d(1);s.length=u.length}},d(i){i&&(w(e),w(n),w(l)),W(s,i)}}}function De(t){let e,n,l,u,s,i,f,o,c,a;l=new be({});function r(B){t[4](B,t[7])}let p={className:"w-full border-b-2 focus:border-b-2",color:"gray",type:"text"};t[7].label!==void 0&&(p.value=t[7].label),s=new K({props:p}),j.push(()=>T(s,"value",r));function g(B){t[5](B,t[7])}function d(...B){return t[6](t[7],...B)}let m={$$slots:{default:[$t]},$$scope:{ctx:t}};return t[7].nextSection!==void 0&&(m.value=t[7].nextSection),o=new we({props:m}),j.push(()=>T(o,"value",g)),o.$on("change",d),{c(){e=S("div"),n=S("div"),N(l.$$.fragment),u=O(),N(s.$$.fragment),f=O(),N(o.$$.fragment),_(n,"class","flex flex-row items-center gap-2 flex-1"),_(e,"class","flex flex-row bg-white items-end gap-2")},m(B,h){$(B,e,h),b(e,n),C(l,n,null),b(n,u),C(s,n,null),b(e,f),C(o,e,null),a=!0},p(B,h){t=B;const Y={};!i&&h&1&&(i=!0,Y.value=t[7].label,P(()=>i=!1)),s.$set(Y);const v={};h&8194&&(v.$$scope={dirty:h,ctx:t}),!c&&h&1&&(c=!0,v.value=t[7].nextSection,P(()=>c=!1)),o.$set(v)},i(B){a||(A(l.$$.fragment,B),A(s.$$.fragment,B),A(o.$$.fragment,B),a=!0)},o(B){y(l.$$.fragment,B),y(s.$$.fragment,B),y(o.$$.fragment,B),a=!1},d(B){B&&w(e),k(l),k(s),k(o)}}}function yt(t){let e,n,l,u,s,i,f,o,c,a=Q(t[0]),r=[];for(let g=0;g<a.length;g+=1)r[g]=De(Pe(t,a,g));const p=g=>y(r[g],1,1,()=>{r[g]=null});return u=new be({}),{c(){e=S("div");for(let g=0;g<r.length;g+=1)r[g].c();n=O(),l=S("div"),N(u.$$.fragment),s=O(),i=S("button"),i.textContent="옵션 추가",_(i,"class","text-blue-400 text-sm"),_(l,"class","flex flex-row items-center gap-2 flex-1 py-2"),_(e,"class","flex flex-col gap-1")},m(g,d){$(g,e,d);for(let m=0;m<r.length;m+=1)r[m]&&r[m].m(e,null);b(e,n),b(e,l),C(u,l,null),b(l,s),b(l,i),f=!0,o||(c=R(i,"click",t[2]),o=!0)},p(g,[d]){if(d&3){a=Q(g[0]);let m;for(m=0;m<a.length;m+=1){const B=Pe(g,a,m);r[m]?(r[m].p(B,d),A(r[m],1)):(r[m]=De(B),r[m].c(),A(r[m],1),r[m].m(e,n))}for(ue(),m=a.length;m<r.length;m+=1)p(m);se()}},i(g){if(!f){for(let d=0;d<a.length;d+=1)A(r[d]);A(u.$$.fragment,g),f=!0}},o(g){r=r.filter(Boolean);for(let d=0;d<r.length;d+=1)y(r[d]);y(u.$$.fragment,g),f=!1},d(g){g&&w(e),W(r,g),k(u),o=!1,c()}}}function Bt(t,e){const n=t.target;e.nextSection=n.value}function St(t,e,n){let{options:l}=e,{survey:u}=e,s=u.sections;function i(){n(0,l=[...l,{label:`옵션 ${l.length}`,value:`${l.length}`}])}function f(a,r){t.$$.not_equal(r.label,a)&&(r.label=a,n(0,l))}function o(a,r){t.$$.not_equal(r.nextSection,a)&&(r.nextSection=a,n(0,l))}const c=(a,r)=>Bt(r,a);return t.$$set=a=>{"options"in a&&n(0,l=a.options),"survey"in a&&n(3,u=a.survey)},t.$$.update=()=>{t.$$.dirty&8&&n(1,s=u.sections)},[l,s,i,u,f,o,c]}class Ct extends M{constructor(e){super(),L(this,e,St,yt,I,{options:0,survey:3})}}function kt(t){let e,n;return e=new Me({props:{$$slots:{default:[Gt]},$$scope:{ctx:t}}}),{c(){N(e.$$.fragment)},m(l,u){C(e,l,u),n=!0},p(l,u){const s={};u&256&&(s.$$scope={dirty:u,ctx:l}),e.$set(s)},i(l){n||(A(e.$$.fragment,l),n=!0)},o(l){y(e.$$.fragment,l),n=!1},d(l){k(e,l)}}}function Nt(t){let e,n;return e=new Me({props:{short:!0,$$slots:{default:[Ot]},$$scope:{ctx:t}}}),{c(){N(e.$$.fragment)},m(l,u){C(e,l,u),n=!0},p(l,u){const s={};u&256&&(s.$$scope={dirty:u,ctx:l}),e.$set(s)},i(l){n||(A(e.$$.fragment,l),n=!0)},o(l){y(e.$$.fragment,l),n=!1},d(l){k(e,l)}}}function qt(t){let e,n,l;function u(i){t[5](i)}let s={survey:t[1]};return t[0]!==void 0&&(s.options=t[0]),e=new Ct({props:s}),j.push(()=>T(e,"options",u)),{c(){N(e.$$.fragment)},m(i,f){C(e,i,f),l=!0},p(i,f){const o={};f&2&&(o.survey=i[1]),!n&&f&1&&(n=!0,o.options=i[0],P(()=>n=!1)),e.$set(o)},i(i){l||(A(e.$$.fragment,i),l=!0)},o(i){y(e.$$.fragment,i),l=!1},d(i){k(e,i)}}}function xt(t){let e,n,l;function u(i){t[4](i)}let s={survey:t[1]};return t[0]!==void 0&&(s.options=t[0]),e=new Le({props:s}),j.push(()=>T(e,"options",u)),{c(){N(e.$$.fragment)},m(i,f){C(e,i,f),l=!0},p(i,f){const o={};f&2&&(o.survey=i[1]),!n&&f&1&&(n=!0,o.options=i[0],P(()=>n=!1)),e.$set(o)},i(i){l||(A(e.$$.fragment,i),l=!0)},o(i){y(e.$$.fragment,i),l=!1},d(i){k(e,i)}}}function Et(t){let e,n,l;function u(i){t[3](i)}let s={survey:t[1]};return t[0]!==void 0&&(s.options=t[0]),e=new Le({props:s}),j.push(()=>T(e,"options",u)),{c(){N(e.$$.fragment)},m(i,f){C(e,i,f),l=!0},p(i,f){const o={};f&2&&(o.survey=i[1]),!n&&f&1&&(n=!0,o.options=i[0],P(()=>n=!1)),e.$set(o)},i(i){l||(A(e.$$.fragment,i),l=!0)},o(i){y(e.$$.fragment,i),l=!1},d(i){k(e,i)}}}function Gt(t){let e;return{c(){e=S("h1"),e.textContent="서술형"},m(n,l){$(n,e,l)},p:x,d(n){n&&w(e)}}}function Ot(t){let e;return{c(){e=S("h1"),e.textContent="단답형"},m(n,l){$(n,e,l)},p:x,d(n){n&&w(e)}}}function Yt(t){let e,n,l,u;const s=[Et,xt,qt,Nt,kt],i=[];function f(o,c){return o[2]==="FIVE-LIKERT"?0:o[2]==="BOOLEAN"?1:o[2]==="MULTIPLE_CHOICE"?2:o[2]==="SHORT"?3:o[2]==="ESSAY"?4:-1}return~(n=f(t))&&(l=i[n]=s[n](t)),{c(){e=S("div"),l&&l.c()},m(o,c){$(o,e,c),~n&&i[n].m(e,null),u=!0},p(o,[c]){let a=n;n=f(o),n===a?~n&&i[n].p(o,c):(l&&(ue(),y(i[a],1,1,()=>{i[a]=null}),se()),~n?(l=i[n],l?l.p(o,c):(l=i[n]=s[n](o),l.c()),A(l,1),l.m(e,null)):l=null)},i(o){u||(A(l),u=!0)},o(o){y(l),u=!1},d(o){o&&w(e),~n&&i[n].d()}}}function It(t,e,n){let{survey:l}=e,{questionType:u}=e,{options:s}=e;const i=[{value:1,label:"매우 동의"},{value:2,label:"동의"},{value:3,label:"중립"},{value:4,label:"비동의"},{value:5,label:"매우 비동의"}],f=[{value:!0,label:"예"},{value:!1,label:"아니오"}];function o(r){s=r,n(0,s),n(2,u)}function c(r){s=r,n(0,s),n(2,u)}function a(r){s=r,n(0,s),n(2,u)}return t.$$set=r=>{"survey"in r&&n(1,l=r.survey),"questionType"in r&&n(2,u=r.questionType),"options"in r&&n(0,s=r.options)},t.$$.update=()=>{if(t.$$.dirty&4)switch(u){case"FIVE-LIKERT":n(0,s=i);break;case"BOOLEAN":n(0,s=f);break;default:n(0,s=[]);break}},[s,l,u,o,c,a]}class jt extends M{constructor(e){super(),L(this,e,It,Yt,I,{survey:1,questionType:2,options:0})}}function Lt(t){let e,n;const l=t[2].default,u=pe(l,t,t[1],null);return{c(){e=S("div"),u&&u.c(),_(e,"class",t[0])},m(s,i){$(s,e,i),u&&u.m(e,null),n=!0},p(s,[i]){u&&u.p&&(!n||i&2)&&ge(u,l,s,s[1],n?de(l,s[1],i,null):me(s[1]),null),(!n||i&1)&&_(e,"class",s[0])},i(s){n||(A(u,s),n=!0)},o(s){y(u,s),n=!1},d(s){s&&w(e),u&&u.d(s)}}}function Mt(t,e,n){let l,{$$slots:u={},$$scope:s}=e;const i="bg-white shadow-md rounded-l border-gray-300 rounded-lg bg-white flex flex-col gap-2 p-4",f="focus-within:shadow-[-5px_0_0_0_rgba(0,0,0,0.3)] focus-within:shadow-blue-400 ";return t.$$set=o=>{n(5,e=E(E({},e),U(o))),"$$scope"in o&&n(1,s=o.$$scope)},t.$$.update=()=>{n(0,l=J(i,e.focus?f:"",e.className??e.className))},e=U(e),[l,s,u]}class ae extends M{constructor(e){super(),L(this,e,Mt,Lt,I,{})}}function Pt(t){let e,n;return{c(){e=G("svg"),n=G("path"),_(n,"d","m12 10.93 5.719-5.72c.146-.146.339-.219.531-.219.404 0 .75.324.75.749 0 .193-.073.385-.219.532l-5.72 5.719 5.719 5.719c.147.147.22.339.22.531 0 .427-.349.75-.75.75-.192 0-.385-.073-.531-.219l-5.719-5.719-5.719 5.719c-.146.146-.339.219-.531.219-.401 0-.75-.323-.75-.75 0-.192.073-.384.22-.531l5.719-5.719-5.72-5.719c-.146-.147-.219-.339-.219-.532 0-.425.346-.749.75-.749.192 0 .385.073.531.219z"),_(e,"clip-rule","evenodd"),_(e,"fill-rule","evenodd"),_(e,"stroke-linejoin","round"),_(e,"stroke-miterlimit","2"),_(e,"viewBox","0 0 24 24"),_(e,"xmlns","http://www.w3.org/2000/svg")},m(l,u){$(l,e,u),b(e,n)},p:x,i:x,o:x,d(l){l&&w(e)}}}class Tt extends M{constructor(e){super(),L(this,e,null,Pt,I,{})}}function Ut(t){let e,n,l,u,s;return n=new Tt({}),{c(){e=S("button"),N(n.$$.fragment),_(e,"class",t[1]),_(e,"title",t[0])},m(i,f){$(i,e,f),C(n,e,null),l=!0,u||(s=R(e,"click",t[2]),u=!0)},p(i,[f]){(!l||f&2)&&_(e,"class",i[1]),(!l||f&1)&&_(e,"title",i[0])},i(i){l||(A(n.$$.fragment,i),l=!0)},o(i){y(n.$$.fragment,i),l=!1},d(i){i&&w(e),k(n),u=!1,s()}}}const Dt="w-8 h-8 text-red-200";function Qt(t,e,n){let l,{title:u="버튼"}=e;function s(i){ie.call(this,t,i)}return t.$$set=i=>{n(3,e=E(E({},e),U(i))),"title"in i&&n(0,u=i.title)},t.$$.update=()=>{n(1,l=J(Dt,e.className??e.className))},e=U(e),[u,l,s]}class Ft extends M{constructor(e){super(),L(this,e,Qt,Ut,I,{title:0})}}function Rt(t){let e,n,l,u,s,i,f,o,c;return{c(){e=S("option"),e.textContent="객관식",n=O(),l=S("option"),l.textContent="5점 리커트",u=O(),s=S("option"),s.textContent="Boolean",i=O(),f=S("option"),f.textContent="단답형",o=O(),c=S("option"),c.textContent="서술형",e.__value="MULTIPLE_CHOICE",D(e,e.__value),l.__value="FIVE-LIKERT",D(l,l.__value),s.__value="BOOLEAN",D(s,s.__value),f.__value="SHORT",D(f,f.__value),c.__value="ESSAY",D(c,c.__value)},m(a,r){$(a,e,r),$(a,n,r),$(a,l,r),$(a,u,r),$(a,s,r),$(a,i,r),$(a,f,r),$(a,o,r),$(a,c,r)},p:x,d(a){a&&(w(e),w(n),w(l),w(u),w(s),w(i),w(f),w(o),w(c))}}}function Vt(t){let e,n,l,u,s,i,f,o,c,a,r,p;function g(v){t[3](v)}let d={className:"bg-gray-100 border-b-2 flex-1"};t[0].question_text!==void 0&&(d.value=t[0].question_text),n=new K({props:d}),j.push(()=>T(n,"value",g));function m(v){t[4](v)}let B={className:" ",$$slots:{default:[Rt]},$$scope:{ctx:t}};t[0].type!==void 0&&(B.value=t[0].type),s=new we({props:B}),j.push(()=>T(s,"value",m));function h(v){t[5](v)}let Y={survey:t[1],questionType:t[0].type};return t[0].options!==void 0&&(Y.options=t[0].options),o=new jt({props:Y}),j.push(()=>T(o,"options",h)),r=new Ft({props:{title:"질문 삭제 버튼",className:"absolute top-6 right-4"}}),r.$on("click",t[6]),{c(){e=S("div"),N(n.$$.fragment),u=O(),N(s.$$.fragment),f=O(),N(o.$$.fragment),a=O(),N(r.$$.fragment),_(e,"class","flex flex-row gap-2 pr-10")},m(v,q){$(v,e,q),C(n,e,null),b(e,u),C(s,e,null),$(v,f,q),C(o,v,q),$(v,a,q),C(r,v,q),p=!0},p(v,q){const te={};!l&&q&1&&(l=!0,te.value=v[0].question_text,P(()=>l=!1)),n.$set(te);const oe={};q&128&&(oe.$$scope={dirty:q,ctx:v}),!i&&q&1&&(i=!0,oe.value=v[0].type,P(()=>i=!1)),s.$set(oe);const _e={};q&2&&(_e.survey=v[1]),q&1&&(_e.questionType=v[0].type),!c&&q&1&&(c=!0,_e.options=v[0].options,P(()=>c=!1)),o.$set(_e)},i(v){p||(A(n.$$.fragment,v),A(s.$$.fragment,v),A(o.$$.fragment,v),A(r.$$.fragment,v),p=!0)},o(v){y(n.$$.fragment,v),y(s.$$.fragment,v),y(o.$$.fragment,v),y(r.$$.fragment,v),p=!1},d(v){v&&(w(e),w(f),w(a)),k(n),k(s),k(o,v),k(r,v)}}}function zt(t){let e,n;return e=new ae({props:{className:"relative",$$slots:{default:[Vt]},$$scope:{ctx:t}}}),{c(){N(e.$$.fragment)},m(l,u){C(e,l,u),n=!0},p(l,[u]){const s={};u&131&&(s.$$scope={dirty:u,ctx:l}),e.$set(s)},i(l){n||(A(e.$$.fragment,l),n=!0)},o(l){y(e.$$.fragment,l),n=!1},d(l){k(e,l)}}}function Jt(t,e,n){let{survey:l}=e,{question:u}=e;const s=xe();function i(a){t.$$.not_equal(u.question_text,a)&&(u.question_text=a,n(0,u))}function f(a){t.$$.not_equal(u.type,a)&&(u.type=a,n(0,u))}function o(a){t.$$.not_equal(u.options,a)&&(u.options=a,n(0,u))}const c=()=>s("remove",u);return t.$$set=a=>{"survey"in a&&n(1,l=a.survey),"question"in a&&n(0,u=a.question)},[u,l,s,i,f,o,c]}class Kt extends M{constructor(e){super(),L(this,e,Jt,zt,I,{survey:1,question:0})}}function Ht(t){let e,n;return{c(){e=G("svg"),n=G("path"),_(n,"d","M9 3h6v-1.75c0-.066-.026-.13-.073-.177-.047-.047-.111-.073-.177-.073h-5.5c-.066 0-.13.026-.177.073-.047.047-.073.111-.073.177v1.75zm11 1h-16v18c0 .552.448 1 1 1h14c.552 0 1-.448 1-1v-18zm-10 3.5c0-.276-.224-.5-.5-.5s-.5.224-.5.5v12c0 .276.224.5.5.5s.5-.224.5-.5v-12zm5 0c0-.276-.224-.5-.5-.5s-.5.224-.5.5v12c0 .276.224.5.5.5s.5-.224.5-.5v-12zm8-4.5v1h-2v18c0 1.105-.895 2-2 2h-14c-1.105 0-2-.895-2-2v-18h-2v-1h7v-2c0-.552.448-1 1-1h6c.552 0 1 .448 1 1v2h7z"),_(e,"width","24"),_(e,"height","24"),_(e,"xmlns","http://www.w3.org/2000/svg"),_(e,"fill-rule","evenodd"),_(e,"clip-rule","evenodd")},m(l,u){$(l,e,u),b(e,n)},p:x,i:x,o:x,d(l){l&&w(e)}}}class Wt extends M{constructor(e){super(),L(this,e,null,Ht,I,{})}}function Zt(t){let e,n,l,u,s;return n=new Wt({}),{c(){e=S("button"),N(n.$$.fragment),_(e,"class",t[1]),_(e,"title",t[0])},m(i,f){$(i,e,f),C(n,e,null),l=!0,u||(s=R(e,"click",t[2]),u=!0)},p(i,[f]){(!l||f&2)&&_(e,"class",i[1]),(!l||f&1)&&_(e,"title",i[0])},i(i){l||(A(n.$$.fragment,i),l=!0)},o(i){y(n.$$.fragment,i),l=!1},d(i){i&&w(e),k(n),u=!1,s()}}}const Xt="w-8 h-8 text-red-200";function en(t,e,n){let l,{title:u="삭제 버튼"}=e;function s(i){ie.call(this,t,i)}return t.$$set=i=>{n(3,e=E(E({},e),U(i))),"title"in i&&n(0,u=i.title)},t.$$.update=()=>{n(1,l=J(Xt,e.className??e.className))},e=U(e),[u,l,s]}class tn extends M{constructor(e){super(),L(this,e,en,Zt,I,{title:0})}}function Qe(t,e,n){const l=t.slice();return l[9]=e[n],l}function nn(t){let e,n,l,u,s,i;function f(r){t[5](r)}let o={className:"py-3 text-xl",placeholder:"섹션 이름을 입력 하세요..."};t[0].title!==void 0&&(o.value=t[0].title),e=new K({props:o}),j.push(()=>T(e,"value",f));function c(r){t[6](r)}let a={placeholder:"섹션 설명(선택 사항)"};return t[0].description!==void 0&&(a.value=t[0].description),u=new K({props:a}),j.push(()=>T(u,"value",c)),{c(){N(e.$$.fragment),l=O(),N(u.$$.fragment)},m(r,p){C(e,r,p),$(r,l,p),C(u,r,p),i=!0},p(r,p){const g={};!n&&p&1&&(n=!0,g.value=r[0].title,P(()=>n=!1)),e.$set(g);const d={};!s&&p&1&&(s=!0,d.value=r[0].description,P(()=>s=!1)),u.$set(d)},i(r){i||(A(e.$$.fragment,r),A(u.$$.fragment,r),i=!0)},o(r){y(e.$$.fragment,r),y(u.$$.fragment,r),i=!1},d(r){r&&w(l),k(e,r),k(u,r)}}}function Fe(t){let e,n,l;function u(i){t[7](i)}let s={question:t[9]};return t[1]!==void 0&&(s.survey=t[1]),e=new Kt({props:s}),j.push(()=>T(e,"survey",u)),e.$on("remove",t[4]),{c(){N(e.$$.fragment)},m(i,f){C(e,i,f),l=!0},p(i,f){const o={};f&1&&(o.question=i[9]),!n&&f&2&&(n=!0,o.survey=i[1],P(()=>n=!1)),e.$set(o)},i(i){l||(A(e.$$.fragment,i),l=!0)},o(i){y(e.$$.fragment,i),l=!1},d(i){k(e,i)}}}function ln(t){let e,n,l,u,s,i,f,o,c,a;e=new ae({props:{$$slots:{default:[nn]},$$scope:{ctx:t}}});let r=Q(t[0].questions),p=[];for(let d=0;d<r.length;d+=1)p[d]=Fe(Qe(t,r,d));const g=d=>y(p[d],1,1,()=>{p[d]=null});return s=new tn({props:{title:"세션 삭제 버튼"}}),s.$on("click",t[8]),{c(){N(e.$$.fragment),n=O();for(let d=0;d<p.length;d+=1)p[d].c();l=O(),u=S("div"),N(s.$$.fragment),i=O(),f=S("button"),f.textContent="질문 더하기 버튼",_(f,"class","border border-solid p-2"),_(f,"title","질문 더하기 버튼"),_(u,"class","border-t-2 mt-4 py-2 pr-4 flex justify-end items-center gap-2")},m(d,m){C(e,d,m),$(d,n,m);for(let B=0;B<p.length;B+=1)p[B]&&p[B].m(d,m);$(d,l,m),$(d,u,m),C(s,u,null),b(u,i),b(u,f),o=!0,c||(a=R(f,"click",t[3]),c=!0)},p(d,m){const B={};if(m&4097&&(B.$$scope={dirty:m,ctx:d}),e.$set(B),m&19){r=Q(d[0].questions);let h;for(h=0;h<r.length;h+=1){const Y=Qe(d,r,h);p[h]?(p[h].p(Y,m),A(p[h],1)):(p[h]=Fe(Y),p[h].c(),A(p[h],1),p[h].m(l.parentNode,l))}for(ue(),h=r.length;h<p.length;h+=1)g(h);se()}},i(d){if(!o){A(e.$$.fragment,d);for(let m=0;m<r.length;m+=1)A(p[m]);A(s.$$.fragment,d),o=!0}},o(d){y(e.$$.fragment,d),p=p.filter(Boolean);for(let m=0;m<p.length;m+=1)y(p[m]);y(s.$$.fragment,d),o=!1},d(d){d&&(w(n),w(l),w(u)),k(e,d),W(p,d),k(s),c=!1,a()}}}function un(t){let e,n;return e=new ae({props:{focus,$$slots:{default:[ln]},$$scope:{ctx:t}}}),{c(){N(e.$$.fragment)},m(l,u){C(e,l,u),n=!0},p(l,[u]){const s={};u&4099&&(s.$$scope={dirty:u,ctx:l}),e.$set(s)},i(l){n||(A(e.$$.fragment,l),n=!0)},o(l){y(e.$$.fragment,l),n=!1},d(l){k(e,l)}}}function sn(t,e,n){let{section:l}=e,{survey:u}=e;const s=xe();function i(){const p={id:String(l.questions.length+1),type:"MULTIPLE_CHOICE",question_text:"질문",options:[],description:""};n(0,l.questions=[...l.questions,p],l)}function f(p){const g=p.detail.id;n(0,l.questions=l.questions.filter(d=>d.id!==g),l)}function o(p){t.$$.not_equal(l.title,p)&&(l.title=p,n(0,l))}function c(p){t.$$.not_equal(l.description,p)&&(l.description=p,n(0,l))}function a(p){u=p,n(1,u),n(0,l)}const r=()=>s("remove",l);return t.$$set=p=>{"section"in p&&n(0,l=p.section),"survey"in p&&n(1,u=p.survey)},t.$$.update=()=>{t.$$.dirty&3&&(l.title,n(1,u),n(0,l))},[l,u,s,i,f,o,c,a,r]}class on extends M{constructor(e){super(),L(this,e,sn,un,I,{section:0,survey:1})}}function rn(t){let e,n,l,u,s,i,f,o,c,a,r,p,g,d,m,B,h,Y,v,q;return{c(){e=G("svg"),n=G("defs"),l=G("style"),u=F(`.cls-1 {\r
                fill: #639;\r
                filter: url(#filter);\r
            }\r
\r
            .cls-2 {\r
                filter: url(#filter-2);\r
            }\r
        `),s=G("filter"),i=G("feOffset"),f=G("feGaussianBlur"),o=G("feFlood"),c=G("feComposite"),a=G("feBlend"),r=G("filter"),p=G("feOffset"),g=G("feGaussianBlur"),d=G("feFlood"),m=G("feComposite"),B=G("feBlend"),h=G("circle"),Y=G("g"),v=G("image"),q=G("image"),_(i,"result","offset"),_(i,"dx","1.364"),_(i,"dy","1.463"),_(i,"in","SourceAlpha"),_(f,"result","blur"),_(f,"stdDeviation","1"),_(o,"result","flood"),_(o,"flood-opacity","0.6"),_(c,"result","composite"),_(c,"operator","in"),_(c,"in2","blur"),_(a,"result","blend"),_(a,"in","SourceGraphic"),_(s,"id","filter"),_(s,"x","5"),_(s,"y","5"),_(s,"width","42"),_(s,"height","42"),_(s,"filterUnits","userSpaceOnUse"),_(p,"result","offset"),_(p,"dx","1.364"),_(p,"dy","1.463"),_(p,"in","SourceAlpha"),_(g,"result","blur"),_(g,"stdDeviation","1"),_(d,"result","flood"),_(d,"flood-opacity","0.6"),_(m,"result","composite"),_(m,"operator","in"),_(m,"in2","blur"),_(B,"result","blend"),_(B,"in","SourceGraphic"),_(r,"id","filter-2"),_(r,"filterUnits","userSpaceOnUse"),_(h,"class","cls-1"),_(h,"cx","25"),_(h,"cy","25"),_(h,"r","19"),_(v,"id","Line_1"),_(v,"data-name","Line 1"),_(v,"x","12"),_(v,"y","24"),_(v,"width","26"),_(v,"height","2"),Ne(v,"xlink:href","data:img/png;base64,iVBORw0KGgoAAAANSUhEUgAAABoAAAACCAYAAABGz8w4AAAAKElEQVQImWL8////fwZaAwYGBgAAAAD//2KihyUMDAwMAAAAAP//AwA9ZQQAVIP1QAAAAABJRU5ErkJggg=="),_(q,"id","Line_1_copy"),_(q,"data-name","Line 1 copy"),_(q,"x","24"),_(q,"y","12"),_(q,"width","2"),_(q,"height","26"),Ne(q,"xlink:href","data:img/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAIAAAAaCAYAAACdM43SAAABLUlEQVQImWL8////fwYGBgYAAAAA//9iAhEMDAwMAAAAAP//gjAYGBgAAAAA//+CMBgYGAAAAAD//4IwGBgYAAAAAP//gjAYGBgAAAAA//+CMBgYGAAAAAD//4IwGBgYAAAAAP//gjAYGBgAAAAA//+CMBgYGAAAAAD//4IwGBgYAAAAAP//gjAYGBgAAAAA//+CMBgYGAAAAAD//4IwGBgYAAAAAP//gjAYGBgAAAAA//+CMBgYGAAAAAD//4IwGBgYAAAAAP//gjAYGBgAAAAA//+CMBgYGAAAAAD//4IwGBgYAAAAAP//gjAYGBgAAAAA//+CMBgYGAAAAAD//4IwGBgYAAAAAP//gjAYGBgAAAAA//+CMBgYGAAAAAD//4IwGBgYAAAAAP//AwCzPQQwJaaJbQAAAABJRU5ErkJggg=="),_(Y,"class","cls-2"),_(e,"xmlns","http://www.w3.org/2000/svg"),_(e,"xmlns:xlink","http://www.w3.org/1999/xlink"),_(e,"width","50"),_(e,"height","50"),_(e,"viewBox","0 0 50 50")},m(te,oe){$(te,e,oe),b(e,n),b(n,l),b(l,u),b(n,s),b(s,i),b(s,f),b(s,o),b(s,c),b(s,a),b(n,r),b(r,p),b(r,g),b(r,d),b(r,m),b(r,B),b(e,h),b(e,Y),b(Y,v),b(Y,q)},p:x,i:x,o:x,d(te){te&&w(e)}}}class fn extends M{constructor(e){super(),L(this,e,null,rn,I,{})}}function cn(t){let e,n,l,u,s;return n=new fn({}),{c(){e=S("button"),N(n.$$.fragment),_(e,"class",t[0]),_(e,"title",t[1])},m(i,f){$(i,e,f),C(n,e,null),l=!0,u||(s=R(e,"click",t[2]),u=!0)},p(i,[f]){(!l||f&1)&&_(e,"class",i[0]),(!l||f&2)&&_(e,"title",i[1])},i(i){l||(A(n.$$.fragment,i),l=!0)},o(i){y(n.$$.fragment,i),l=!1},d(i){i&&w(e),k(n),u=!1,s()}}}const an="";function _n(t,e,n){let{title:l="버튼"}=e,{className:u=""}=e;u+=an;function s(i){ie.call(this,t,i)}return t.$$set=i=>{"title"in i&&n(1,l=i.title),"className"in i&&n(0,u=i.className)},[u,l,s]}class pn extends M{constructor(e){super(),L(this,e,_n,cn,I,{title:1,className:0})}}function Re(t,e,n){const l=t.slice();return l[7]=e[n],l}function dn(t){let e,n,l,u,s,i;function f(r){t[5](r)}let o={className:"py-4 text-2xl"};t[0].title!==void 0&&(o.value=t[0].title),e=new K({props:o}),j.push(()=>T(e,"value",f));function c(r){t[6](r)}let a={placeholder:"설문지 설명(선택 사항)"};return t[0].description!==void 0&&(a.value=t[0].description),u=new K({props:a}),j.push(()=>T(u,"value",c)),{c(){N(e.$$.fragment),l=O(),N(u.$$.fragment)},m(r,p){C(e,r,p),$(r,l,p),C(u,r,p),i=!0},p(r,p){const g={};!n&&p&1&&(n=!0,g.value=r[0].title,P(()=>n=!1)),e.$set(g);const d={};!s&&p&1&&(s=!0,d.value=r[0].description,P(()=>s=!1)),u.$set(d)},i(r){i||(A(e.$$.fragment,r),A(u.$$.fragment,r),i=!0)},o(r){y(e.$$.fragment,r),y(u.$$.fragment,r),i=!1},d(r){r&&w(l),k(e,r),k(u,r)}}}function Ve(t){let e,n;return e=new on({props:{survey:t[0],section:t[7]}}),e.$on("remove",t[2]),{c(){N(e.$$.fragment)},m(l,u){C(e,l,u),n=!0},p(l,u){const s={};u&1&&(s.survey=l[0]),u&1&&(s.section=l[7]),e.$set(s)},i(l){n||(A(e.$$.fragment,l),n=!0)},o(l){y(e.$$.fragment,l),n=!1},d(l){k(e,l)}}}function gn(t){let e,n,l,u,s,i,f;n=new ae({props:{focus,$$slots:{default:[dn]},$$scope:{ctx:t}}});let o=Q(t[0].sections),c=[];for(let r=0;r<o.length;r+=1)c[r]=Ve(Re(t,o,r));const a=r=>y(c[r],1,1,()=>{c[r]=null});return i=new pn({props:{className:"absolute right-8 bottom-8"}}),i.$on("click",t[1]),{c(){e=S("main"),N(n.$$.fragment),l=O(),u=S("div");for(let r=0;r<c.length;r+=1)c[r].c();s=O(),N(i.$$.fragment),_(u,"class","w-full flex-1 flex flex-col gap-2 "),_(e,"class","relative w-full min-h-full bg-violet-100 flex flex-col gap-2 justify-center p-8")},m(r,p){$(r,e,p),C(n,e,null),b(e,l),b(e,u);for(let g=0;g<c.length;g+=1)c[g]&&c[g].m(u,null);b(e,s),C(i,e,null),f=!0},p(r,[p]){const g={};if(p&1025&&(g.$$scope={dirty:p,ctx:r}),n.$set(g),p&5){o=Q(r[0].sections);let d;for(d=0;d<o.length;d+=1){const m=Re(r,o,d);c[d]?(c[d].p(m,p),A(c[d],1)):(c[d]=Ve(m),c[d].c(),A(c[d],1),c[d].m(u,null))}for(ue(),d=o.length;d<c.length;d+=1)a(d);se()}},i(r){if(!f){A(n.$$.fragment,r);for(let p=0;p<o.length;p+=1)A(c[p]);A(i.$$.fragment,r),f=!0}},o(r){y(n.$$.fragment,r),c=c.filter(Boolean);for(let p=0;p<c.length;p+=1)y(c[p]);y(i.$$.fragment,r),f=!1},d(r){r&&w(e),k(n),W(c,r),k(i)}}}function mn(t,e,n){let l={title:"제목 없는 설문지",description:"",sections:[]};function u(a){n(0,l=JSON.parse(a))}function s(){return l}function i(a){const r={id:String(l.sections.length+1),title:"",description:"",questions:[]};n(0,l.sections=[...l.sections,r],l)}function f(a){const r=a.detail.id;n(0,l.sections=l.sections.filter(p=>p.id!==r),l)}function o(a){t.$$.not_equal(l.title,a)&&(l.title=a,n(0,l))}function c(a){t.$$.not_equal(l.description,a)&&(l.description=a,n(0,l))}return[l,i,f,u,s,o,c]}class hn extends M{constructor(e){super(),L(this,e,mn,gn,I,{setSurvey:3,getSurvey:4})}get setSurvey(){return this.$$.ctx[3]}get getSurvey(){return this.$$.ctx[4]}}return hn}();