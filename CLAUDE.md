# Diplom — Server Monitoring Android App

## Project Overview

Android diploma project: a mobile app for monitoring the status of servers. The product vision is that **all data (users, servers, chat history) lives on the user's device** — there is no centralized backend in the business requirements. **Server status values, metrics, and AI chat responses are mocked** so the app feels like a real working system without any real network or LLM calls.

## Business Requirements

### Authentication Flow

- **Welcome screen** is the first screen after launch. It shows two buttons: **Login** and **Register**.
- **Registration** requires only:
  - Email
  - Password
  - A toggle switch that marks the account as **Administrator** or **Regular user**
- **Login** requires only:
  - Email
  - Password
  - A **Login** button
- On successful login, the user is taken to the **Servers** screen.

### Servers Screen

- Displays the list of servers belonging to the currently logged-in user.
- A **FAB (floating action button) in the bottom-right** opens an "Add server" dialog.
- The dialog has three input fields — **Name**, **IP address**, **Port** — and an **Add** button.
- When a new server is added, it appears in the list with one of two mocked statuses:
  - **`OK`** — tapping the row opens a **server detail page** with full information (temperature, RAM, HDD, etc., all mocked).
  - **`ERROR`** — tapping the row shows a **Toast containing a JSON error payload** indicating the server could not be connected. The detail page is NOT opened in this case.
- Which status a newly added server receives is decided by the mock layer (e.g., random, or based on a rule — implementation detail, but must be mocked).

### Shared Sidebar (Navigation Drawer)

- Every authenticated screen exposes the **same sidebar**. It must be implemented **once** as a shared component and reused — not re-declared per activity.
- Sidebar options:
  1. **Servers**
  2. **Users**
  3. **AI**
  4. **Log Out**

### Users Screen

- Lists all registered users.
- Role-gated behavior:
  - **Administrator** — can delete any user by tapping the **trash-can icon** next to the row.
  - **Regular user** — read-only. The delete icon must not be shown / must be disabled.
- Role is determined by the `admin` flag on the currently logged-in `User`.

### AI Screen

- Chat-style interface simulating a real LLM assistant.
- Two ways to interact:
  1. **"Summarize" button** — produces a mocked summary of **all servers linked to the current user** plus recommendations (e.g., "Server X is running hot, consider checking cooling").
  2. **Free-form query** — a text input field and a **Send** button. Example: the user types *"What is the current CPU temperature of Aboba's server?"* and after a short delay the AI responds with a temperature value in natural language.
- The response **must feel like a real working LLM**:
  - Short simulated "thinking" delay (a few seconds) before the answer appears.
  - Natural-language output, not raw JSON.
- All AI responses are mocked — no real LLM / API calls.

### Data Storage

- Business vision: **all data stored locally on the device**. No centralized database in the product concept, only Firebase, who already used.
- Everything that would normally come from a real server (metrics, status, AI replies) is **mocked**.

## Current Tech Stack

The repository currently contains a partial Java/XML skeleton. These are the actual technologies in the code today:

| Area | Technology |
|---|---|
| Language | **Java** |
| UI toolkit | **XML layouts + ViewBinding** (no Jetpack Compose) |
| Auth (current code) | **Firebase Auth** |
| Database (current code) | **Firebase Realtime Database** |
| Navigation | AndroidX Navigation component 2.6.0 |
| Theme | Material Design 3 — `Theme.Diplom` |
| minSdk | 24 |
| compileSdk / targetSdk | 34 |
| AGP | 8.3.0 |
| Package / Application ID | `com.example.diplom` |

## Important Note on Storage Direction

**Current code:** uses Firebase Auth + Firebase Realtime Database (which is a centralized cloud backend).

**Do not silently remove or replace Firebase.** Before switching the storage backend (e.g., to Room + SharedPreferences) or removing the Firebase dependency, **ask the user** which direction they want to take for that session's work.

## Conventions for Claude

- **Language:** Stay with **Java**. Do not migrate existing files to Kotlin unless the user explicitly asks.
- **UI:** Use **XML layouts + ViewBinding**. Do not introduce Jetpack Compose.
- **Mocking:** Server statuses, metrics, and AI chat responses must be **mocked** — never add real network calls or real LLM integrations.
- **Shared sidebar:** Implement the drawer **once** as a reusable component (base activity, shared layout include, or fragment) and use it on every authenticated screen. Do not copy-paste the drawer per activity.
- **Role gating:** The `admin` flag on `User` gates destructive actions — especially user deletion in the Users screen. Non-admin users must not even see the delete affordance.
- **AI feel:** Mocked AI answers should include a short artificial delay and be phrased as natural language, not raw data dumps.

## Entry Points

- **Launcher:** `MainActivity` (see `app/src/main/AndroidManifest.xml`).
- **Authenticated hub:** `Servers` activity (shown after successful login).
- See [app/CLAUDE.md](app/CLAUDE.md) for the current source layout and a gap list of screens that still need to be built.
