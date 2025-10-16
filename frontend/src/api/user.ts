import { http } from '@/lib/http'
import type { UserInfo } from '@/types/user'

/**
 * 用户 API 服务
 */
export const userApi = {
  /**
   * 获取用户列表
   */
  getUserList() {
    return http.get<UserInfo[]>('/users')
  },

  /**
   * 获取用户详情
   * @param id 用户ID
   */
  getUserById(id: number) {
    return http.get<UserInfo>(`/users/${id}`)
  },

  /**
   * 创建用户
   * @param data 用户数据
   */
  createUser(data: Partial<UserInfo>) {
    return http.post<UserInfo>('/users', data)
  },

  /**
   * 更新用户
   * @param id 用户ID
   * @param data 用户数据
   */
  updateUser(id: number, data: Partial<UserInfo>) {
    return http.put<UserInfo>(`/users/${id}`, data)
  },

  /**
   * 删除用户
   * @param id 用户ID
   */
  deleteUser(id: number) {
    return http.delete(`/users/${id}`)
  },
}
