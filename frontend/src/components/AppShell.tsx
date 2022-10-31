import React, { useEffect, useState } from 'react'
import { AppShell, Navbar, Header, NativeSelect, Image, Modal, Group, Button, SimpleGrid, Box, Grid, Text } from '@mantine/core';
import PlotDiagram from './PlotDiagram';
import Input from './Input';
import AppService from '../services/AppService';
import covid from '../covid.svg'

export default function AppShellProject() {
    const [names, setNames] = useState([]);
    const [name,setName] = useState('Nazwa symulacji'); 
    const [opened, setOpened] = useState(false);

    useEffect(() => {
      AppService.getAllNames().then((res) => {setNames(res.data)})
    },([]));
 

    return (
        <AppShell
          padding="md"
          header={<Header height={120} p="xs">
            <Grid sx={{ width: 1300}} mx="auto">
               <div style={{ width: 100 }}>
                <Image
                  radius="md"
                  src={covid}
                  alt="Random unsplash image"
                />
              </div>
              <Text
                    sx={{paddingTop: 30, paddingLeft:20}} 
                    weight={700} 
                    size="xl" 
                    variant="gradient"
                    gradient={{ from: 'indigo', to: 'cyan', deg: 45 }}>
                      Aplikacja do prognozowania przebiegu COVID-19
              </Text>
            </Grid>
          </Header>}>

          <Box sx={{ width: 1300}} mx="auto">

            <Modal
              opened={opened}
              onClose={() => setOpened(false)}
              title="Podaj parametry symulacji"
            >
              <Input/>
            </Modal>
            <Grid  sx={{paddingBottom: 20}}>

              <NativeSelect
                sx={{ width: 900}} mx="auto"
                data={names}
                placeholder="Nazwa symulacji"
                label="Wybierz nazwę symulacji"
                onChange={(event) => setName(event.currentTarget.value)} >
              </NativeSelect>

              <Group sx={{ width: 400, paddingTop: 25, paddingLeft:10, align: "right"}} mx="auto">
                <Button 
                    variant="gradient"
                    gradient={{ from: 'indigo', to: 'cyan', deg: 45 }}
                    onClick={() => setOpened(true)}
                >
                  Nowa symulacja
                </Button>
              </Group>
          </Grid>

          <Text>Poniżej znajduje się wykres przedstawiający przebieg danych parametrów symulacji. 
                Po prawej stronie możesz wyłączyć lub włączyć dane serie wykresu tak aby dane były bardziej widoczne.
          </Text>

          <PlotDiagram name={name}></PlotDiagram>

        </Box>
        </AppShell>
      );
}

