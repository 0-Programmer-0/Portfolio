# CREATING EMG SIGNAL AND PLOTTING
import numpy as np
import matplotlib.pyplot as plt
#matplotlib inline
import scipy as sp
from scipy import signal

# Le o arquivo txt com os valores dos sinais EMG
with open('emg_microV.txt', 'r') as file:
    # Inicializar uma lista para armazenar os valores da primeira coluna
    emg_trapezio = []
    emg_deltoide = []
    emg_biceps = []
    
    # Iterar por cada linha do arquivo
    for linha in file:
        # Dividir a linha em colunas, assumindo que estão separadas por espaços
        colunas = linha.split()
        
        # Adicionar o valor da primeira coluna à lista
        if colunas:  # Verificar se a linha não está vazia
            try:
                valor1 = colunas[0].replace(',', '.')
                emg_trapezio.append(float(valor1))
                
                valor2 = colunas[1].replace(',', '.')
                emg_deltoide.append(float(valor2))
                
                valor3 = colunas[2].replace(',', '.')
                emg_biceps.append(float(valor3))
            except ValueError:
                print(f"Valor inválido encontrado: {colunas[0]}")
                
# Le o arquivo txt com os valores dos sinais EMG - PORCENTAGEM
with open('emg_porcentagem.txt', 'r') as file:
    # Inicializar uma lista para armazenar os valores da primeira coluna
    porcentagem_trapezio = []
    porcentagem_deltoide = []
    porcentagem_biceps = []
    
    # Iterar por cada linha do arquivo
    for linha in file:
        # Dividir a linha em colunas, assumindo que estão separadas por espaços
        colunas = linha.split()
        
        # Adicionar o valor da primeira coluna à lista
        if colunas:  # Verificar se a linha não está vazia
            try:
                valor4 = colunas[0].replace(',', '.')
                porcentagem_trapezio.append(float(valor4))
                
                valor5 = colunas[1].replace(',', '.')
                porcentagem_deltoide.append(float(valor5))
                
                valor6 = colunas[2].replace(',', '.')
                porcentagem_biceps.append(float(valor6))
            except ValueError:
                print(f"Valor inválido encontrado: {colunas[0]}")


# tempo dos devidos emg - PORCENTAGEM
time_trapezio_porc = np.array([i/50 for i in range(0, len(porcentagem_trapezio), 1)])
time_deltoide_porc = np.array([i/50 for i in range(0, len(porcentagem_deltoide), 1)])
time_biceps_porc = np.array([i/50 for i in range(0, len(porcentagem_biceps), 1)])

# tempo dos devidos emg
time_trapezio = np.array([i/50 for i in range(0, len(emg_trapezio), 1)])
time_deltoide = np.array([i/50 for i in range(0, len(emg_deltoide), 1)])
time_biceps = np.array([i/50 for i in range(0, len(emg_biceps), 1)])

# process EMG signal: remove mean
emg_correctmean_trapezio = emg_trapezio - np.mean(emg_trapezio)
emg_correctmean_deltoide = emg_deltoide - np.mean(emg_deltoide)
emg_correctmean_biceps = emg_biceps - np.mean(emg_biceps)

#FILTERING-RECTIFY
#create bandpass filter for EMG
high = 20/(2000/2)
low = 450/(2000/2)
b, a = sp.signal.butter(4, [high,low], btype='bandpass')

# process EMG signal: filter EMG
emg_filtered_trapezio = sp.signal.filtfilt(b, a, emg_correctmean_trapezio)
emg_filtered_deltoide = sp.signal.filtfilt(b, a, emg_correctmean_deltoide)
emg_filtered_biceps = sp.signal.filtfilt(b, a, emg_correctmean_biceps)

#sinal EMG retificado
emg_retificado_trapezio = abs(emg_filtered_trapezio)
emg_retificado_deltoide = abs(emg_filtered_deltoide)
emg_retificado_biceps = abs(emg_filtered_biceps)

# EMG Puro ====================================================================
# Plot Trapézio
fig = plt.figure()
plt.subplot(3, 1, 1).set_title('Trapézio Superior Esquerdo')
plt.plot(time_trapezio, emg_trapezio)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(0, 60)
plt.ylabel('EMG (a.u.)')

# Plot Deltoide
plt.subplot(3, 1, 2).set_title('Deltoide Lateral Esquerdo')
plt.plot(time_deltoide, emg_deltoide)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(0, 60)
plt.ylabel('EMG (a.u.)')

# Plot Bíceps
plt.subplot(3, 1, 3).set_title('Bíceps Braquial Esquerdo')
plt.plot(time_biceps, emg_biceps)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(0, 60)
plt.xlabel('Time (s)')
plt.ylabel('EMG (a.u.)')

#ajuste de espaçamento
plt.subplots_adjust(hspace=0.5)

fig_name = 'fig_emg_simulation.png'
fig.set_size_inches(w=11,h=7)
fig.savefig(fig_name)

#==============================================================================

# EMG média ajustada ====================================================================
# Plot Trapézio
fig = plt.figure()
plt.subplot(3, 1, 1).set_title('Trapézio Superior Esquerdo')
plt.plot(time_trapezio, emg_correctmean_trapezio)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(0, 60)
plt.ylabel('EMG (a.u.)')

# Plot Deltoide
plt.subplot(3, 1, 2).set_title('Deltoide Lateral Esquerdo')
plt.plot(time_deltoide, emg_correctmean_deltoide)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(0, 60)
plt.ylabel('EMG (a.u.)')

# Plot Bíceps
plt.subplot(3, 1, 3).set_title('Bíceps Braquial Esquerdo')
plt.plot(time_biceps, emg_correctmean_biceps)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(0, 60)
plt.xlabel('Time (s)')
plt.ylabel('EMG (a.u.)')

#ajuste de espaçamento
plt.subplots_adjust(hspace=0.5)

fig_name = 'fig_emg_simulation.png'
fig.set_size_inches(w=11,h=7)
fig.savefig(fig_name)

#============================================================================== 

# EMG filtrado ====================================================================
# Plot Trapézio
fig = plt.figure()
plt.subplot(3, 1, 1).set_title('Trapézio Superior Esquerdo')
plt.plot(time_trapezio, emg_filtered_trapezio)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(0, 60)
plt.ylabel('EMG (a.u.)')

# Plot Deltoide
plt.subplot(3, 1, 2).set_title('Deltoide Lateral Esquerdo')
plt.plot(time_deltoide, emg_filtered_deltoide)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(0, 60)
plt.ylabel('EMG (a.u.)')

# Plot Bíceps
plt.subplot(3, 1, 3).set_title('Bíceps Braquial Esquerdo')
plt.plot(time_biceps, emg_filtered_biceps)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(0, 60)
plt.xlabel('Time (s)')
plt.ylabel('EMG (a.u.)')

#ajuste de espaçamento
plt.subplots_adjust(hspace=0.5)

fig_name = 'fig_emg_simulation.png'
fig.set_size_inches(w=11,h=7)
fig.savefig(fig_name)

#============================================================================== 

# EMG retificado ====================================================================
# Plot Trapézio
fig = plt.figure()
plt.subplot(3, 1, 1).set_title('Trapézio Superior Esquerdo')
plt.plot(time_trapezio, emg_retificado_trapezio)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(0, 60)
plt.ylabel('EMG (a.u.)')

# Plot Deltoide
plt.subplot(3, 1, 2).set_title('Deltoide Lateral Esquerdo')
plt.plot(time_deltoide, emg_retificado_deltoide)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(0, 60)
plt.ylabel('EMG (a.u.)')

# Plot Bíceps
plt.subplot(3, 1, 3).set_title('Bíceps Braquial Esquerdo')
plt.plot(time_biceps, emg_retificado_biceps)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(0, 60)
plt.xlabel('Time (s)')
plt.ylabel('EMG (a.u.)')

#ajuste de espaçamento
plt.subplots_adjust(hspace=0.5)

fig_name = 'fig_emg_simulation.png'
fig.set_size_inches(w=11,h=7)
fig.savefig(fig_name)

#==============================================================================  

# EMG em Porcentagem ====================================================================
# Plot Trapézio
fig = plt.figure()
plt.subplot(3, 1, 1).set_title('Trapézio Superior Esquerdo')
plt.plot(time_trapezio_porc, porcentagem_trapezio)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(-60, 60)
plt.ylabel('EMG (%)')

# Plot Deltoide
plt.subplot(3, 1, 2).set_title('Deltoide Lateral Esquerdo')
plt.plot(time_deltoide_porc, porcentagem_deltoide)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(-60, 60)
plt.ylabel('EMG (%)')

# Plot Bíceps
plt.subplot(3, 1, 3).set_title('Bíceps Braquial Esquerdo')
plt.plot(time_biceps_porc, porcentagem_biceps)
plt.locator_params(axis='x', nbins=6)
plt.locator_params(axis='y', nbins=10)
plt.xlim(0, 30)
plt.ylim(-60, 60)
plt.xlabel('Time (s)')
plt.ylabel('EMG (%)')

#ajuste de espaçamento
plt.subplots_adjust(hspace=0.5)

fig_name = 'fig_emg_simulation.png'
fig.set_size_inches(w=11,h=7)
fig.savefig(fig_name)

#==============================================================================  