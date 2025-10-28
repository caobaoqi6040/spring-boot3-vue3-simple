<template>

  <div class="container mx-auto p-6">
    <div class="mb-6">
      <h1 class="text-3xl font-bold mb-2">用户列表</h1>
      <p class="text-gray-600">演示页面 - 验证前后端连接状态</p>
    </div>

    <!-- 加载状态 -->
    <div v-if="loading" class="flex items-center justify-center py-12">
      <div class="text-center">
        <div class="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-gray-900"></div>
        <p class="mt-4 text-gray-600">加载中...</p>
      </div>
    </div>

    <!-- 错误状态 -->
    <div v-else-if="error" class="bg-red-50 border border-red-200 rounded-lg p-4">
      <h3 class="text-red-800 font-semibold mb-2">加载失败</h3>
      <p class="text-red-600">{{ error }}</p>
      <button
          @click="fetchUsers"
          class="mt-4 px-4 py-2 bg-red-600 text-white rounded hover:bg-red-700 transition-colors"
      >
        重试
      </button>
    </div>

    <!-- 用户列表 -->
    <div v-else-if="users.length > 0" class="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
      <div
          v-for="user in users"
          :key="user.id"
          class="bg-white border border-gray-200 rounded-lg p-4 shadow-sm hover:shadow-md transition-shadow"
      >
        <div class="flex items-start gap-4">
          <!-- 头像 -->
          <img
              :src="user.avatar"
              :alt="user.username"
              class="w-16 h-16 rounded-full object-cover"
          />

          <!-- 用户信息 -->
          <div class="flex-1 min-w-0">
            <h3 class="font-semibold text-lg truncate">{{ user.username }}</h3>
            <p class="text-sm text-gray-600 truncate">{{ user.email }}</p>
            <p class="text-xs text-gray-400 mt-1">ID: {{ user.id }}</p>
          </div>
        </div>

        <!-- 时间信息 -->
        <div class="mt-4 pt-4 border-t border-gray-100 text-xs text-gray-500">
          <div class="flex justify-between">
            <span>创建时间</span>
            <span>{{ user.createTime }}</span>
          </div>
          <div class="flex justify-between mt-1">
            <span>更新时间</span>
            <span>{{ user.updateTime }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- 空状态 -->
    <div v-else class="text-center py-12">
      <p class="text-gray-500">暂无用户数据</p>
    </div>

    <!-- 状态信息 -->
    <div class="mt-8 bg-blue-50 border border-blue-200 rounded-lg p-4">
      <h3 class="font-semibold text-blue-900 mb-2">连接状态</h3>
      <div class="text-sm text-blue-800 space-y-1">
        <p>API 地址: <code class="bg-blue-100 px-2 py-1 rounded">{{ apiBaseUrl }}</code></p>
        <p>总用户数: <strong>{{ users.length }}</strong></p>
        <p>
          状态:
          <span v-if="!loading && !error" class="text-green-600 font-semibold">✓ 连接成功</span>
          <span v-else-if="error" class="text-red-600 font-semibold">✗ 连接失败</span>
          <span v-else class="text-gray-600">加载中...</span>
        </p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import {onMounted, ref} from 'vue'
import {userApi} from '@/api/user'
import type {UserInfo} from '@/types/user'

const users = ref<UserInfo[]>([])
const loading = ref(false)
const error = ref<string | null>(null)
const apiBaseUrl = import.meta.env.VITE_API_BASE_URL

const fetchUsers = async () => {
  loading.value = true
  error.value = null

  try {
    const response = await userApi.getUserList()
    users.value = response.data
  } catch (err: any) {
    error.value = err.message || '获取用户列表失败'
    console.error('Failed to fetch users:', err)
  } finally {
    loading.value = false
  }
}

const deleteUser = async () => {
  try {
    const response = await userApi.deleteUser(1)
    users.value = response.data
  } catch (err: any) {
    error.value = err.message || '删除用户失败'
    console.error('Failed to fetch users:', err)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchUsers()
  deleteUser()
})
</script>
