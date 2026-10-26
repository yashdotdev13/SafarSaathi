# Contributing to SafarSaathi

Thank you for your interest in contributing to SafarSaathi! Contributions of all sizes are welcome, including bug fixes, tests, documentation improvements, and new features.

## Before you start

- Read the [README](README.md) to understand the project and its services.
- Check existing issues and pull requests before starting work.
- For substantial changes or new features, open or comment on an issue first so we can agree on the approach.
- If an issue is already assigned or someone is actively working on it, coordinate before duplicating the work.

## Development setup

SafarSaathi is a Java/Spring Boot microservices project. Check the README and each service's configuration for the currently supported Java, Maven, databases, and infrastructure requirements.

1. Fork the repository and clone your fork.
2. Create a focused branch from `main`, for example `feat/trip-budget` or `fix/jwt-validation`.
3. Configure required environment variables and local dependencies. Never commit credentials, API keys, tokens, private keys, or production data.
4. Build and test the relevant module or service before making a pull request.

Use the project's documented Docker Compose setup when it is appropriate for the change. Do not assume external services or secrets are available in a clean checkout.

## Making changes

- Keep changes focused and consistent with the existing architecture and conventions.
- Prefer small, reviewable pull requests over unrelated bundles of changes.
- Add or update unit tests for business logic and regression tests for bugs.
- Add integration tests when behavior crosses service, database, Kafka, or other infrastructure boundaries and the test can be maintained reliably.
- Update documentation and configuration examples when behavior or setup changes.
- Avoid adding new dependencies unless they provide clear value; explain significant dependency choices in the pull request.
- Do not include generated build output, IDE files, secrets, or unrelated formatting changes.
- For API changes, consider validation, authorization, backward compatibility, and consistent error responses.
- For event-driven changes, consider duplicate delivery, retries, failure handling, and event compatibility.
- For AI/tooling changes, test invalid or incomplete plans, tool failures, authorization boundaries, timeouts, and user-data isolation where applicable.

## Tests and checks

Run the tests for every module you change. From the repository root, use the project's documented Maven commands and the CI workflow as the source of truth for required checks.

At minimum:

- Run the relevant unit tests.
- Run relevant integration tests when applicable.
- Run the build or verification command used by CI for the affected module(s).
- Review the diff and ensure no secrets or unrelated files are included.

If a test cannot be run locally because a dependency or credential is unavailable, mention that clearly in the pull request and describe what you did verify. Do not mark unrun tests as passing.

## Pull requests

1. Make sure your branch is up to date with `main` where practical.
2. Use a clear, descriptive title. Conventional Commit-style titles are encouraged, for example:
   - `fix(auth): validate JWT claims`
   - `test(trip): cover trip ownership rules`
   - `docs: clarify local development setup`
3. Explain the problem and why the change is needed.
4. Summarize the implementation and list the services/modules affected.
5. Include the tests and checks you ran, along with their results.
6. Link the related issue using GitHub's closing keywords when appropriate (for example, `Closes #123`).
7. Include API examples or screenshots when they help reviewers understand a user-visible change.

A pull request should be ready for review, pass applicable CI checks, and address reviewer feedback. Maintainers may request changes or additional tests before merging.

## Reporting bugs and proposing features

When opening an issue, include enough detail for others to reproduce or evaluate it.

For bugs, provide:

- A concise title and a clear description of the expected versus actual behavior.
- Steps to reproduce, with minimal example requests or configuration where possible.
- The affected service/module, relevant logs, and environment details.
- What you have already tried.
- A sanitized example; never include secrets, personal data, or production credentials.

For feature requests, describe the problem, proposed behavior, relevant use cases, and any compatibility or security considerations. For larger features, discuss the design before submitting a large implementation.

## Security issues

Please do not publish exploitable vulnerabilities, credentials, tokens, or sensitive personal data in a public issue. Report security concerns privately to the repository owner through an appropriate private contact method, and include only the information needed to investigate safely.

## Code of conduct

Be respectful, constructive, and welcoming. Critique ideas and code, not people. Assume good intent, give actionable feedback, and help keep discussions accessible to contributors with different levels of experience.

Thank you for helping improve SafarSaathi!
