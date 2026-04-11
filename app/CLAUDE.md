# app — Android Application Module

Single Android app module for the Diplom server-monitoring project. All application code, resources, and the manifest live here. See the root [CLAUDE.md](../CLAUDE.md) for the full business requirements and project-wide conventions.

## Source Layout

- **Java sources:** `app/src/main/java/com/example/diplom/`
  - Activities, data models, and socket utility stubs.
- **XML layouts / resources:** `app/src/main/res/`
  - `layout/` — one XML file per screen.
  - `values/`, `drawable/`, `menu/`, `navigation/` — theme, icons, menus, nav graphs.
- **Manifest:** `app/src/main/AndroidManifest.xml` — declares every activity and the launcher intent filter.
- **Module Gradle file:** `app/build.gradle.kts` — dependencies (Firebase BOM, Navigation, Material, Lifecycle, ViewBinding enabled).

## Key Files

| File | Purpose |
|---|---|
| `MainActivity.java` | Launcher / welcome screen (entry point in the manifest). |
| `Login.java` | Email + password login. Currently backed by Firebase Auth. |
| `Registration.java` | Email + password + admin/regular toggle on sign-up. |
| `Servers.java` | Server list screen shown after login. Target for FAB + add-server flow. |
| `NewServer.java` / `servers_new.java` | Add-server flow — capture name, IP, port. |
| `ShowServer.java` | Detail view for a single server (only reachable when status is `OK`). |
| `User.java` | Data model: `id`, `name`, `surname`, `mail`, `password`, `admin`. |
| `Server.java` | Data model: `id`, `IP`, `Port`, `status`, `temp`, `ram`, `hdd`. |
| `SocketClient.java` / `SocketServer.java` | Minimal socket utility stubs — not wired to real servers. |
| `AndroidManifest.xml` | Registers all activities above. |

## Gaps vs. Business Requirements

The following pieces from the root CLAUDE.md are **not yet implemented** in this module and are the natural next work items:

- [ ] **Shared sidebar / navigation drawer** (Servers / Users / AI / Log Out) reused across all authenticated screens.
- [ ] **Users screen** — list of users with admin-only delete affordance (trash-can icon).
- [ ] **AI screen** — chat UI with "Summarize" button, free-form query input, Send button, and mocked LLM responses with simulated delay.
- [ ] **Mock data layer** — source of truth for server statuses (`OK` / `ERROR`), mocked metrics (temp, RAM, HDD), mocked JSON error payload for failed-connect toasts, and mocked AI replies.
- [ ] **Role-based UI gating** using `User.admin` to hide destructive controls from regular users.
- [ ] **Add-server flow wiring** — the dialog/screen exists, but the business rule "new server gets `OK` or `ERROR` status and behaves accordingly" still needs to be applied.

## Pointer

For business requirements, mocked-behavior rules, language/UI conventions, and the note about the Firebase-vs-local-storage tension, see the root [CLAUDE.md](../CLAUDE.md).
