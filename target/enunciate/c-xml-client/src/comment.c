#include <enunciate-common.c>
#ifndef DEF_comment_ns0_comment_H
#define DEF_comment_ns0_comment_H

/**
 * (no documentation provided)
 */
struct comment_ns0_comment {

};

/**
 * Reads a Comment from XML. The reader is assumed to be at the start element.
 *
 * @param reader The XML reader.
 * @return The Comment, or NULL in case of error.
 */
static struct comment_ns0_comment *xmlTextReaderReadNs0CommentType(xmlTextReaderPtr reader);

/**
 * Writes a Comment to XML.
 *
 * @param writer The XML writer.
 * @param _comment The Comment to write.
 * @return The bytes written (may be 0 in case of buffering) or -1 in case of error.
 */
static int xmlTextWriterWriteNs0CommentType(xmlTextWriterPtr writer, struct comment_ns0_comment *_comment);

/**
 * Frees the elements of a Comment.
 *
 * @param _comment The Comment to free.
 */
static void freeNs0CommentType(struct comment_ns0_comment *_comment);

#endif /* DEF_comment_ns0_comment_H */
#ifndef DEF_comment_ns0_comment_M
#define DEF_comment_ns0_comment_M

/**
 * Reads a Comment from XML. The reader is assumed to be at the start element.
 *
 * @return the Comment, or NULL in case of error.
 */
static struct comment_ns0_comment *xmlTextReaderReadNs0CommentType(xmlTextReaderPtr reader) {
  int status, depth;
  void *_child_accessor;
  struct comment_ns0_comment *_comment = calloc(1, sizeof(struct comment_ns0_comment));




  return _comment;
}

/**
 * Writes a Comment to XML.
 *
 * @param writer The XML writer.
 * @param _comment The Comment to write.
 * @return The total bytes written, or -1 on error;
 */
static int xmlTextWriterWriteNs0CommentType(xmlTextWriterPtr writer, struct comment_ns0_comment *_comment) {
  int status, totalBytes = 0, i;
  xmlChar *binaryData;

  return totalBytes;
}

/**
 * Frees the elements of a Comment.
 *
 * @param _comment The Comment to free.
 */
static void freeNs0CommentType(struct comment_ns0_comment *_comment) {
  int i;
}
#endif /* DEF_comment_ns0_comment_M */
