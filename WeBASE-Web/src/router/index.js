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
const main = resolve => require(['@/views/index/index'], resolve);
const home = resolve => require(['@/views/home/home'], resolve);
const blockInfo = resolve => require(['@/views/blockInfo/blockInfo'], resolve);
const transactionInfo = resolve => require(['@/views/transactionInfo/transactionInfo'], resolve);
const front = resolve => require(['@/views/front/index'], resolve);
const host = resolve => require(['@/views/front/host'], resolve);
const node = resolve => require(['@/views/front/node'], resolve);
const newFront = resolve => require(['@/views/front/front'], resolve);
const hostDetail = resolve => require(['@/views/front/components/hostDetail'], resolve);
const contract = resolve => require(['@/views/chaincode/contract'], resolve);
const oldContract = resolve => require(['@/views/chaincode/index'], resolve)
const privateKeyManagement = resolve => require(['@/views/privateKeyManagement/index'], resolve);
const hostMetric = resolve => require(['@/views/hostMetric'], resolve);
const nodesMetric = resolve => require(['@/views/nodesMetric'], resolve);
const accountInfo = resolve => require(['@/views/account/accountInfo'], resolve);
const transactionCharts = resolve => require(['@/views/transactionCharts/transactionCharts'], resolve);
const unusualUser = resolve => require(['@/views/unusualUser/unusualUser'], resolve);
const unusualContract = resolve => require(['@/views/unusualContract/unusualContract'], resolve);
const certificate = resolve => require(['@/views/certificate'], resolve);
const permission = resolve => require(['@/views/permission'], resolve);
const configManagement = resolve => require(['@/views/configManagement'], resolve);
const cnsManagement = resolve => require(['@/views/cnsManagement'], resolve);
const CRUDServiceManagement = resolve => require(['@/views/CRUDServiceManagement'], resolve);
const emailAlarm = resolve => require(['@/views/alarm/emailAlarm'], resolve);
const emailAlarmType = resolve => require(['@/views/alarm/emailAlarmType'], resolve);
const contractEvent = resolve => require(['@/views/contractEvent'], resolve);
const blockEvent = resolve => require(['@/views/blockEvent'], resolve);
const groupManagement = resolve => require(['@/views/groupManagement'], resolve);
const abiList = resolve => require(['@/views/abiList'], resolve);
const parseAbi = resolve => require(['@/views/parseAbi'], resolve);
const committeeMgmt = resolve => require(['@/views/committeeMgmt'], resolve);
const developerMgmt = resolve => require(['@/views/developerMgmt'], resolve);
const newPermission = resolve => require(['@/views/permission/newPermission'], resolve);
const eventCheck = resolve => require(['@/views/eventCheck'], resolve);
const contractWarehouse = resolve => require(['@/views/contractWarehouse'], resolve);
const onlineTools = resolve => require(['@/views/onlineTools'], resolve); 
const appManagement = resolve => require(['@/views/appManagement'], resolve); 
const toolsContract = resolve => require(['@/views/toolsContract'], resolve);
const bigScreen = resolve => require(['@/views/bigScreen/index'], resolve);
Vue.use(Router);
const routes = [
    {
        path: '/',
        nameKey: "main",
        redirect: '/main',
    },
    {
        path: '/login',
        nameKey: "login",
        name: 'login',
        component: resolve => require(['@/views/login/login'], resolve),
    },
    {
        path: '/bigScreen',
        nameKey: "bigScreen",
        name: 'bigScreen',
        component: bigScreen,
        menuShow: false,
        leaf:false,
        iconCls: 'wbs-icon-overview sidebar-icon',
        // children: [
        //     {
        //         path: '/bigScreen', component: bigScreen, name: '????????????', nameKey: "bigScreen", menuShow: false, meta: { requireAuth: true }
        //     }
        // ]
    },
    {
        path: '/main',
        name: 'main',
        // redirect: '/home',
        leaf: true,
        nameKey: "dataOverview",
        menuShow: true,
        iconCls: 'wbs-icon-gailan sidebar-icon',
        component: main,
        children: [
            {
                path: '/home', component: home, name: '????????????', nameKey: "dataOverview", menuShow: true, meta: { requireAuth: true }
            }
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
        ]
    },
    {
        path: '/',
        component: main,
        name: '?????????',
        nameKey: "chainTitle",
        leaf: false,
        menuShow: true,
        iconCls: 'wbs-icon-group sidebar-icon',
        children: [
            { path: '/host', component: host, name: '????????????', nameKey: "hostMgrTitle", menuShow: true, meta: { requireAuth: true } },
            { path: '/front', component: front, name: '????????????', nameKey: "nodeTitle", menuShow: true, meta: { requireAuth: true } },
            { path: '/node/:id', component: node, name: '????????????', nameKey: "nodeTitle", menuShow: false, meta: { requireAuth: true } },
            { path: '/newNode', component: newFront, name: '????????????', nameKey: "nodeTitle", menuShow: false, meta: { requireAuth: true } },
            { path: '/hostDetail', component: hostDetail, name: '????????????', nameKey: "nodeDetail", leaf: false, menuShow: false, meta: { requireAuth: true } }
        ]
    },
    {
        path: '/',
        component: main,
        name: '????????????',
        nameKey: "groupManagement",
        leaf: true,
        menuShow: false,
        iconCls: 'wbs-icon-group sidebar-icon',
        children: [
            { path: '/groupManagement', component: groupManagement, name: '????????????', nameKey: "groupManagement", menuShow: true, meta: { requireAuth: true } },

        ]
    },
    {
        path: '/',
        component: main,
        name: '????????????',
        nameKey: "contractTitle",
        leaf: false,
        menuShow: true,
        iconCls: 'wbs-icon-heyueguanli sidebar-icon',
        children: [
            { path: '/contract', component: contract, name: '??????IDE', nameKey: "contractIDE", menuShow: true, meta: { requireAuth: true } },
            { path: '/contractList', component: oldContract, name: '????????????', nameKey: "contractList", menuShow: true, meta: { requireAuth: true } },
            { path: '/contractWarehouse', component: contractWarehouse, name: '????????????', nameKey: 'contractWarehouse', menuShow: true, meta: { requireAuth: false } },
            // { path: '/abiList', component: abiList, name: 'Abi??????', nameKey: "abiList", menuShow: false, meta: { requireAuth: true } },
            { path: '/parseAbi', component: parseAbi, name: '??????Abi', nameKey: "parseAbi", menuShow: false, meta: { requireAuth: true } },
            { path: '/cnsManagement', component: cnsManagement, name: 'CNS??????', nameKey: "CNSmanager", menuShow: true, meta: { requireAuth: true } },
            { path: '/CRUDServiceManagement', component: CRUDServiceManagement, name: 'CRUD', nameKey: "CRUDServiceManagement", menuShow: true, meta: { requireAuth: true } },
            { path: '/eventCheck', component: eventCheck, name: 'Event ??????', nameKey: "checkEvent", menuShow: true, meta: { requireAuth: false } },
            // { path: '/onlineTools', component: onlineTools, name: '????????????', enName: 'onlineTools', menuShow: true, meta: { requireAuth: false } }, 
            { path: '/toolsContract', component: toolsContract, name: '????????????', nameKey: 'toolsContract', menuShow: false, meta: { requireAuth: false } }, 
        ]
    },
    {
        path: '/',
        component: main,
        name: '????????????',
        nameKey: "PrivateKey",
        leaf: true,
        menuShow: true,
        iconCls: 'wbs-icon-lock sidebar-icon',
        children: [
            { path: '/privateKeyManagement', component: privateKeyManagement, name: '????????????', nameKey: "PrivateKey", menuShow: true, meta: { requireAuth: true } }
        ]
    },
    {
        path: '/',
        component: main,
        name: '????????????',
        nameKey: "systemManager",
        leaf: false,
        menuShow: true,
        iconCls: 'wbs-icon-xitongguanli sidebar-icon',
        children: [
            { path: '/newPermission', component: newPermission, name: '????????????', nameKey: "newPermission", menuShow: true, meta: { requireAuth: true } },
            // { path: '/permission', component: permission, name: '????????????', nameKey: "permission", menuShow: true, meta: { requireAuth: true } },
            { path: '/configManagement', component: configManagement, name: '????????????', nameKey: "configManager", menuShow: true, meta: { requireAuth: true } },
            { path: '/certificate', component: certificate, name: '????????????', nameKey: "certificate", menuShow: true, meta: { requireAuth: true } },
            // { path: '/committeeMgmt', component: committeeMgmt, name: '???????????????', nameKey: "committeeMgmt", menuShow: true, meta: { requireAuth: true } },
            // { path: '/developerMgmt', component: developerMgmt, name: '??????????????????', nameKey: "developerMgmt", menuShow: true, meta: { requireAuth: true } },

        ]
    },
    {
        path: '/',
        component: main,
        name: '????????????',
        nameKey: "appManagement",
        leaf: true,
        menuShow: true,
        iconCls: 'wbs-icon-deploy sidebar-icon',
        children: [
            { path: '/appManagement', component: appManagement, name: '????????????', nameKey: 'appManagement', menuShow: true, meta: { requireAuth: false } },
        ]
    },
    {
        path: '/',
        component: main,
        name: '????????????',
        nameKey: "systemMonitor",
        leaf: false,
        menuShow: true,
        iconCls: 'wbs-icon-monitor sidebar-icon',
        children: [
            { path: '/nodesMetric', component: nodesMetric, name: '????????????', nameKey: "nodesMonitor", menuShow: true, meta: { requireAuth: false } },
            { path: '/hostMetric', component: hostMetric, name: '????????????', nameKey: "hostMonitor", menuShow: true, meta: { requireAuth: false } },
            { path: '/emailAlarm', component: emailAlarm, name: '??????????????????', nameKey: "emailAlarm", menuShow: true, meta: { requireAuth: false } },
            { path: '/emailAlarmType', component: emailAlarmType, name: '??????????????????', nameKey: "emailAlarmType", menuShow: true, meta: { requireAuth: false } }
        ]
    },
    {
        path: '/',
        component: main,
        name: '????????????',
        nameKey: "transactionAudit",
        leaf: false,
        menuShow: true,
        iconCls: 'wbs-icon-regulatory sidebar-icon',
        children: [
            { path: '/transactionCharts', component: transactionCharts, name: '????????????', nameKey: "userTransaction", menuShow: true, meta: { requireAuth: true } },
            { path: '/unusualUser', component: unusualUser, name: '????????????', nameKey: "unusualUser", menuShow: true, meta: { requireAuth: true } },
            { path: '/unusualContract', component: unusualContract, name: '????????????', nameKey: "unusualContract", menuShow: true, meta: { requireAuth: true } },
            // { path: '/externalAccount', component: externalAccount, name: '????????????', nameKey: "externalAccount", menuShow: true, meta: { requireAuth: false } },
            // { path: '/externalContract', component: externalContract, name: '????????????', nameKey: "externalContract", menuShow: true, meta: { requireAuth: false } },
        ]
    },
    {
        path: '/',
        component: main,
        name: '????????????',
        nameKey: "subscribeEvent",
        leaf: false,
        menuShow: true,
        iconCls: 'wbs-icon-dingyue sidebar-icon',
        children: [
            { path: '/blockEvent', component: blockEvent, name: '????????????', nameKey: "blockEvent", menuShow: true, meta: { requireAuth: true } },
            { path: '/contractEvent', component: contractEvent, name: '??????Event??????', nameKey: "contractEvent", menuShow: true, meta: { requireAuth: true } },

        ]
    },
    {
        path: '/',
        component: main,
        name: '????????????',
        nameKey: "accountManagement",
        leaf: true,
        menuShow: false,
        iconCls: 'wbs-icon-key sidebar-icon',
        children: [
            { path: '/accountInfo', component: accountInfo, name: '????????????', nameKey: "accountManagement", menuShow: true, meta: { requireAuth: true } }
        ]
    }

]
const router = new Router({
    routes
});
const originalPush = Router.prototype.push;
Router.prototype.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
    return originalPush.call(this, location).catch(err => err)
}

export default router
