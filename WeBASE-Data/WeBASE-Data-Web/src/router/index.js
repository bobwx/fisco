/*
 * Copyright 2014-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import Vue from 'vue'
import Router from 'vue-router'
import { getCookie } from '@/util/util'

const main = resolve => require(['@/views/index/main'], resolve);
const home = resolve => require(['@/views/home'], resolve);
const chain = resolve => require(['@/views/chain'], resolve);
const group = resolve => require(['@/views/group'], resolve);
const overview = resolve => require(['@/views/overview'], resolve);
const blockInfo = resolve => require(['@/views/blockInfo'], resolve);
const transactionInfo = resolve => require(['@/views/transactionInfo'], resolve);
const contractInfo = resolve => require(['@/views/contractInfo'], resolve);
const userInfo = resolve => require(['@/views/userInfo'], resolve);
const keywordConfig = resolve => require(['@/views/keywordConfig/index.vue'], resolve);
const alarm = resolve => require(['@/views/alarm/index.vue'], resolve);
const appAlarm = resolve => require(['@/views/appAlarm/index.vue'], resolve);
const txAlarm = resolve => require(['@/views/txAlarm/index.vue'], resolve);
Vue.use(Router);
const routes = [
    {
        path: '/',
        redirect: '/login',
    },
    {
        path: '/login',
        name: 'login',
        component: resolve => require(['@/views/login/index.vue'], resolve),
    },
    {
        path: '/main',
        name: 'main',
        redirect: '/home',
        leaf: true,
        nameKey: "home",    
        menuShow: true,
        iconCls: 'wbs-icon-gailan sidebar-icon',
        component: main,
        children: [
            {
                path: '/home', component: home, name: '??????',nameKey: "home", menuShow: true, meta: { requireAuth: true }
            }
        ]
    },
    {
        path: '/',
        component: main,
        name: '?????????',
        nameKey: "chain",
        leaf: true,
        menuShow: true,
        iconCls: 'wbs-icon-group sidebar-icon',
        children: [
            { path: '/chain', component: chain, name: '?????????', nameKey: "nodeTitle", menuShow: true, meta: { requireAuth: true } },
        ]
    }, 
    {
        path: '/',
        component: main,
        name: '??????',
        nameKey: "group",
        leaf: true,
        menuShow: false,
        iconCls: 'wbs-icon-group sidebar-icon',
        children: [
            { path: '/group', component: group, name: '??????', nameKey: "group", menuShow: true, meta: { requireAuth: true } },
        ]
    },
    {
        path: '/',
        component: main,
        name: '??????',
        nameKey: "overview",
        leaf: true,
        menuShow: false,
        iconCls: 'wbs-icon-group sidebar-icon',
        children: [
            { path: '/overview/:chainId/:chainName/:groupId', component: overview, name: '??????', nameKey: "overview", menuShow: true, meta: { requireAuth: true } },
        ]
    },
    {
        path: '/',
        component: main,
        name: '????????????',
        nameKey: "blockBrowsing",
        menuShow: false,
        iconCls: 'wbs-icon-overview sidebar-icon',
        children: [
            { path: '/blockInfo', component: blockInfo, name: '????????????', nameKey: "blockTitle", menuShow: true, meta: { requireAuth: true } },
            { path: '/transactionInfo', component: transactionInfo, name: '????????????', nameKey: "transactionInfo", menuShow: true, meta: { requireAuth: true } },
            { path: '/contractInfo', component: contractInfo, name: '????????????', nameKey: "contractInfo", menuShow: true, meta: { requireAuth: true } },
            { path: '/userInfo', component: userInfo, name: '????????????', nameKey: "userInfo", menuShow: true, meta: { requireAuth: true } },
            
        ]
    },
    {
        path: '/',
        component: main,
        name: '??????',
        nameKey: "keywordConfig",
        leaf: true,
        menuShow: true,
        iconCls: 'wbs-icon-key sidebar-icon',
        children: [
            { path: '/keywordConfig', component: keywordConfig, name: '??????', nameKey: "keywordConfig", menuShow: true, meta: { requireAuth: true } }
        ]
    }, 
    {
        path: '/',
        component: main,
        name: '????????????',
        nameKey: "alarm",
        // leaf: true,
        menuShow: true,
        iconCls: 'wbs-icon-wenjian sidebar-icon',
        children: [
            { path: '/txAlarm', component: txAlarm, name: '????????????', nameKey: "txAlarm", menuShow: true, meta: { requireAuth: true } },
            { path: '/appAlarm', component: appAlarm, name: '????????????', nameKey: "appAlarm", menuShow: true, meta: { requireAuth: true } }
        ]
    }, 
]
const router = new Router({
    routes
});

const originalPush = Router.prototype.push;
Router.prototype.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
    return originalPush.call(this, location).catch(err => err)
}
Router.prototype.push = function push(location) {
    return originalPush.call(this, location).catch(err => err)
}
export default router
