# 📝 To-Do List Compose - Android Jetpack Compose

Este projeto é um aplicativo Android nativo desenvolvido para a disciplina de Soluções Mobile no Centro Universitário SATC (UNISATC).

## 🎯 Objetivo

O objetivo principal é desenvolver um aplicativo utilizando Jetpack Compose para gerenciar uma lista de tarefas (To-Do List), aplicando conceitos de persistência de dados local, arquitetura MVVM e gerenciamento de estados.

## 📑 Tema: Persistência e Personalização

Nesta versão, o usuário pode gerenciar suas atividades diárias com foco em organização e produtividade. O diferencial está na persistência total, garantindo que as informações e preferências não se percam ao fechar o "Manto Sagrado" (o aplicativo).

## 🚀 Funcionalidades e Requisitos

* **Persistência com Room:** Implementação de banco de dados SQLite local para salvar, ler e atualizar tarefas.
* **Gerenciamento de Tema (Extra):** Utilização de **SharedPreferences** para salvar a preferência de Tema Claro/Escuro do usuário de forma permanente.
* **Arquitetura Reativa:** Uso de **ViewModel** e **StateFlow** para garantir que a interface reflita as mudanças do banco de dados em tempo real.
* **CRUD de Tarefas:** Capacidade de inserir novas tarefas e marcar tarefas concluídas com atualização imediata no banco de dados.
* **UI Moderna:** Interface construída 100% em **Jetpack Compose**, focando em componentes reutilizáveis e Material Design 3.

## 🛠️ Tecnologias

* **Linguagem:** Kotlin
* **UI:** Jetpack Compose
* **Banco de Dados:** Room Persistence Library
* **Persistência de Preferências:** SharedPreferences
* **Ambiente:** Android Studio

---

**Acadêmico:** Arthur  
**Professor:** Thyerri Mezzari  
**Curso:** Engenharia de Software - 5ª Fase
