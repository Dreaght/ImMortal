# Architecture

`:app` is Compose Multiplatform platform entrypoints module.
Which is itself ONE OF representation layers.
Later it will be a sibling to `:qt-app`, `:gtk-app`, etc.

`:shared` is Decompose components module, abstract UI components
which serve all representation layers for navigation and lifecycle flow and data models.

`:core` is a headless module for data and pure business logic. It is agnostic from a framework.

`:matrix` is a Trixnity framework module.

Both `:shared` and `:matrix` depend on `:core`, because `:shared` must know the business logic interfaces,
and `:matrix` implements them, while being injected into `:shared` via Dependency Injection (DI)
from `:app` and other platform entrypoints.

`:server` module is just yet another service, not an application, therefore it does not depend on `:shared`,
but depends on `:core` directly. `:matrix` is injected as an implementation directly via DI.


## Dependency graph

- `:app` -> `:shared` -> `:core`.
- `:app` -> `:matrix` -> `:core`.
- `:shared` -> `:core`. Expects an implementation of business logic.
  Therefore, you can either inject `:matrix` to it or a mock.
- `:server` -> `:core`. Expects an implementation of business logic.
  Therefore, you can either inject `:matrix` to it or a mock.
- `:matrix` -> `:core`. Serves an implementation of `:core`. It is conceptually a sibling to a test mock.
