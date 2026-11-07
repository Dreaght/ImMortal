# ImMortal

An extensible and modular Instant Messaging (IM)
communication with decoupled layers of application and transport.

## Mission

[1] PLATFORM INTEGRATION AND SOVEREIGN:

This is not Electron slop. You use a representation layer of choice:
- [X] Compose Multiplatform
- [ ] QT
- [ ] GTK
- [ ] WinUI
- [ ] TUI / CLI

Compose Multiplatform: use platform-specific bootstrappers with no compromise
on platform integration or runtime choice, with a stable fallback.

Or use platform-specific toolkit skins of choice.

[2] AIR-GAPPED:

Use a transport protocol of choice (decoupled):
- [ ] Matrix Client-Server protocol.
- [ ] TCP/UDP-based fallback.
- [ ] Tor/I2P proxying.
- [ ] Your own protocol.

There's zero assumption that standard internet connection is perfect and traffic is never shaped.
A transport protocol does not know about the application and your data. The application does not know about the transport.

## Why Kotlin Multiplatform

- **A runtime and toolkit independence.** The core application stays stable,
  what gets swapped is platform-specific boostrapper and feature superset.
  I can maintain many entrypoint modules simultaneously, completely decoupled
  from the actual application. A user can choose what works the best right now
  on his system, with no locked-in vendored enforcement, such as frameworks like
  Electron or Flutter.
- Combined with Decompose framework, it is the architecture of modular and extensible
  system, which from the evolutionary biology perspective are the qualities of
  a survivable organism, therefore the project is called **ImMortal**.

## Status

Work in progress and in active research.
