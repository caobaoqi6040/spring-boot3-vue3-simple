import axios, {AxiosError, type AxiosInstance, type AxiosRequestConfig, type AxiosResponse} from 'axios'
import {toast} from 'vue-sonner'
import {useAuthStore} from '@/stores/auth'

export interface ApiResponse<T = any> {
    data?: T
    message?: string
    code?: number
}

export interface ErrorResponse {
    type?: string
    title?: string
    status?: number
    detail?: string
    instance?: string
    'time-stamp'?: string
}

class HttpClient {

    private readonly instance: AxiosInstance

    constructor() {
        this.instance = axios.create({
            baseURL: import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080/api',
            timeout: 15000,
            headers: {
                'Content-Type': 'application/json',
            },
        })

        this.setupInterceptors()
    }

    private setupInterceptors(): void {
        // 请求拦截器
        this.instance.interceptors.request.use(
            (config) => {
                // 从 pinia store 获取 token
                const authStore = useAuthStore()
                if (authStore.token) {
                    config.headers.Authorization = `Bearer ${authStore.token}`
                }

                // 开发环境日志
                if (import.meta.env.DEV) {
                    console.log('请求:', config.method?.toUpperCase(), config.url, config.data)
                }

                return config
            },
            (error: AxiosError) => {
                console.error('请求错误:', error)
                return Promise.reject(error)
            },
        )

        // 响应拦截器
        this.instance.interceptors.response.use(
            (response: AxiosResponse) => {
                // 开发环境日志
                if (import.meta.env.DEV) {
                    console.log('响应:', response.config.url, response.data)
                }

                return response
            },
            (error: AxiosError<ErrorResponse>) => {
                return this.handleError(error)
            },
        )
    }

    private handleError(error: AxiosError<ErrorResponse>): Promise<never> {

        let errorMessage = '发生未知错误'

        if (error.response) {
            const {status, data} = error.response

            // spring boot problem detail
            if (data && typeof data === 'object') {
                errorMessage = data.detail || data.title || errorMessage
            }

            switch (status) {
                case 400:
                    toast.error(errorMessage || '请求参数错误')
                    break
                case 401:
                    toast.error('未授权，请重新登录')
                    // 清除 token 并跳转到登录页
                    const authStore = useAuthStore()
                    authStore.clearToken()
                    // router.push('/login')
                    break
                case 403:
                    toast.error('无权限访问')
                    break
                case 404:
                    toast.error('请求的资源不存在')
                    break
                case 500:
                    toast.error(errorMessage || '服务器错误，请稍后重试')
                    break
                default:
                    toast.error(errorMessage)
            }
        } else if (error.request) {
            toast.error('网络错误，请检查您的网络连接')
            console.error('网络错误:', error.request)
        } else {
            toast.error(errorMessage)
            console.error('请求配置错误:', error.message)
        }

        return Promise.reject(error)
    }

    public get<T = any>(url: string, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
        return this.instance.get<T>(url, config)
    }

    public post<T = any>(
        url: string,
        data?: any,
        config?: AxiosRequestConfig,
    ): Promise<AxiosResponse<T>> {
        return this.instance.post<T>(url, data, config)
    }

    public put<T = any>(
        url: string,
        data?: any,
        config?: AxiosRequestConfig,
    ): Promise<AxiosResponse<T>> {
        return this.instance.put<T>(url, data, config)
    }

    public patch<T = any>(
        url: string,
        data?: any,
        config?: AxiosRequestConfig,
    ): Promise<AxiosResponse<T>> {
        return this.instance.patch<T>(url, data, config)
    }

    public delete<T = any>(url: string, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
        return this.instance.delete<T>(url, config)
    }

    public getAxiosInstance(): AxiosInstance {
        return this.instance
    }
}

export const http = new HttpClient()
