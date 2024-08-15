#import "comment.h"
#ifndef DEF_COMMENTNS0Comment_M
#define DEF_COMMENTNS0Comment_M

/**
 * (no documentation provided)
 */
@implementation COMMENTNS0Comment

- (void) dealloc
{
  [super dealloc];
}
@end /* implementation COMMENTNS0Comment */

/**
 * Internal, private interface for JAXB reading and writing.
 */
@interface COMMENTNS0Comment (JAXB) <JAXBReading, JAXBWriting, JAXBType>

@end /*interface COMMENTNS0Comment (JAXB)*/

/**
 * Internal, private implementation for JAXB reading and writing.
 */
@implementation COMMENTNS0Comment (JAXB)

/**
 * Read an instance of COMMENTNS0Comment from an XML reader.
 *
 * @param reader The reader.
 * @return An instance of COMMENTNS0Comment defined by the XML reader.
 */
+ (id<JAXBType>) readXMLType: (xmlTextReaderPtr) reader
{
  COMMENTNS0Comment *_cOMMENTNS0Comment = [[COMMENTNS0Comment alloc] init];
  NS_DURING
  {
    [_cOMMENTNS0Comment initWithReader: reader];
  }
  NS_HANDLER
  {
    _cOMMENTNS0Comment = nil;
    [localException raise];
  }
  NS_ENDHANDLER

  [_cOMMENTNS0Comment autorelease];
  return _cOMMENTNS0Comment;
}

/**
 * Initialize this instance of COMMENTNS0Comment according to
 * the XML being read from the reader.
 *
 * @param reader The reader.
 */
- (id) initWithReader: (xmlTextReaderPtr) reader
{
  return [super initWithReader: reader];
}

/**
 * Write the XML for this instance of COMMENTNS0Comment to the writer.
 * Note that since we're only writing the XML type,
 * No start/end element will be written.
 *
 * @param reader The reader.
 */
- (void) writeXMLType: (xmlTextWriterPtr) writer
{
  [super writeXMLType:writer];
}

//documentation inherited.
- (BOOL) readJAXBAttribute: (xmlTextReaderPtr) reader
{
  void *_child_accessor;

  if ([super readJAXBAttribute: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (BOOL) readJAXBValue: (xmlTextReaderPtr) reader
{
  return [super readJAXBValue: reader];
}

//documentation inherited.
- (BOOL) readJAXBChildElement: (xmlTextReaderPtr) reader
{
  id __child;
  void *_child_accessor;
  int status, depth;

  if ([super readJAXBChildElement: reader]) {
    return YES;
  }

  return NO;
}

//documentation inherited.
- (int) readUnknownJAXBChildElement: (xmlTextReaderPtr) reader
{
  return [super readUnknownJAXBChildElement: reader];
}

//documentation inherited.
- (void) readUnknownJAXBAttribute: (xmlTextReaderPtr) reader
{
  [super readUnknownJAXBAttribute: reader];
}

//documentation inherited.
- (void) writeJAXBAttributes: (xmlTextWriterPtr) writer
{
  int status;

  [super writeJAXBAttributes: writer];

}

//documentation inherited.
- (void) writeJAXBValue: (xmlTextWriterPtr) writer
{
  [super writeJAXBValue: writer];
}

/**
 * Method for writing the child elements.
 *
 * @param writer The writer.
 */
- (void) writeJAXBChildElements: (xmlTextWriterPtr) writer
{
  int status;
  id __item;
  id __item_copy;
  NSEnumerator *__enumerator;

  [super writeJAXBChildElements: writer];

}
@end /* implementation COMMENTNS0Comment (JAXB) */

#endif /* DEF_COMMENTNS0Comment_M */
