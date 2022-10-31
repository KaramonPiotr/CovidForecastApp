import { MantineProvider } from '@mantine/core'
import React from 'react'
import AppShellProject from './components/AppShell'

export default function App() {
  return (
    <div>
    <MantineProvider>
      <AppShellProject></AppShellProject>
    </MantineProvider>
</div>
  )
}

