# Talkster

A both extensible and modular communication with decoupled layers of application and transport.

## Mission

[1] PLATFORM INTEGRATION:

This is not Electron slop. You use a representation layer of choice:
- [X] Compose Multiplatform
- [ ] QT
- [ ] GTK
- [ ] WinUI
- [ ] TUI / CLI

[2] AIR-GAPPED:

Use a transport protocol of choice (decoupled):
- [ ] Matrix Client-Server protocol.
- [ ] TCP/UDP-based fallback.
- [ ] Tor/I2P proxying.
- [ ] Your own protocol.

There's zero assumption that standard internet connection is perfect and traffic is never shaped.
A transport protocol does not know about the application and your data. The application does not know about the transport.

## Status

Work in progress and in active research.
