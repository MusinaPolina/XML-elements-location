# IDE Plugin for Persistent XML Element Identification

![Build](https://github.com/MusinaPolina/XML-elements-location/workflows/Build/badge.svg)

<!-- Plugin description -->
This is an IDE plugin that provides a mechanism for locating XML elements within a document that
would work even after their content is changed. The plugin can be used by technical writers who use
XML to describe content, and later HTML is generated from this XML.
<!-- Plugin description end -->

## Usage
To generate persistent identifiers for XML elements, follow these steps:

1. Open an XML document in your IDE.
2. Select the XML element and first click Ctrl+Alt+A second click A.
3. The plugin will generate a link to the element that could be found in the left toolbar XML
   Elements.

To locate an element in the document using its persistent ID, follow these steps:

1. Open the XML Elements toolbar.
2. Left-click on the element from the list.
3. The search results will show you the location of the element in the document, even if its content
   has been changed since the ID attribute was generated.
4. The generated link could be renamed or removed by right-click on the element in the toolbar.

## Limitations
- The plugin only works with XML documents and XML elements.
- The plugin cannot guarantee that the generated IDs will be completely persistent, as changes to
  the document structure may still cause them to become outdated.

  

## Installation

- Manually:

  Download
  the [latest release](https://github.com/MusinaPolina/XML-elements-location/releases/latest) and
  install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from
  disk...</kbd>

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
