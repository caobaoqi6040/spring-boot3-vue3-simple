<script setup lang="ts">
import {RouterLink, RouterView} from 'vue-router'
import {Button} from '@/components/ui/button'
</script>

<template>
  <div class="min-h-screen flex flex-col">
    <header class="border-b bg-white shadow-sm">
      <div class="container mx-auto px-4 py-4">
        <nav class="flex items-center gap-3">
          <RouterLink to="/">
            <Button variant="ghost">Home</Button>
          </RouterLink>
          <RouterLink to="/flex">
            <Button variant="ghost">Flex</Button>
          </RouterLink>
          <RouterLink to="/grid">
            <Button variant="ghost">Grid</Button>
          </RouterLink>
          <RouterLink to="/about">
            <Button variant="ghost">About</Button>
          </RouterLink>
          <RouterLink to="/users">
            <Button variant="ghost">用户列表</Button>
          </RouterLink>
        </nav>
      </div>
    </header>
    <main class="flex-1 container mx-auto px-4 py-8">
      <RouterView/>
    </main>
  </div>
</template>

