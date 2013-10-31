/**
 * 	Copyright (c) 2013, jmochel
 * 	All rights reserved.
 *
 * 	Redistribution and use in source and binary forms, with or without
 * 	modification, are permitted provided that the following conditions are met:
 *
 * 	1. Redistributions of source code must retain the above copyright notice, this
 * 	   list of conditions and the following disclaimer.
 *
 * 	2. Redistributions in binary form must reproduce the above copyright notice,
 * 	   this list of conditions and the following disclaimer in the documentation
 * 	   and/or other materials provided with the distribution.
 *
 * 	THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * 	ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * 	WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * 	DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * 	ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * 	(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * 	LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * 	ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * 	(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * 	SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * 	The views and conclusions contained in the software and documentation are those
 * 	of the authors and should not be interpreted as representing official policies,
 * 	either expressed or implied, of the FreeBSD Project.
 */

package org.saltations.tracker.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.text.MessageFormat.format;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.IOException;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.ext.DatabaseClosedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.query.Predicate;
import com.db4o.query.Query;
import com.eaio.uuid.UUID;
import com.google.common.base.Preconditions;
import com.google.common.base.StandardSystemProperty;
import com.google.common.base.Strings;

@Slf4j
@Data
public class TestBase {

	/**
	 * ID for the current set of test runs on a per test basis.
	 */

	private UUID currSessionID = new UUID();

	/**
	 * Data Store for Db4O
	 */

	private ObjectContainer objStore = null;

	private void setupPersistentStore(final ITestContext ctx) throws IOException {

		if ( objStore == null)
		{
			System.out.println("Configuring Persistent Store");
			
			EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
			config.common().activationDepth(18);
			config.common().updateDepth(18);
			
			File dbFile = getQualifiedFile(ctx, "datastore", "db4o");
			
			objStore = Db4oEmbedded.openFile(config,dbFile.getAbsolutePath());
			
			assertNotNull(objStore);
		}
	}

	protected File getQualifiedFile(final ITestContext ctx, String moniker, String ext)
	{
		return new File(ctx.getOutputDirectory() + "/" + ctx.getName() + "-" + moniker + "-" + currSessionID + ext);
	}
	
	private void teardownPersistentStore(final ITestContext context) {
		log.debug("Tearing down Persistent Store");
		
		if (objStore != null)
		{
			objStore.close();
		}
	}

	private void cleanDatastore()
	{
		deleteAnyStored(Object.class);
	}

	@BeforeMethod(alwaysRun=true)
	public void methodSetup(final ITestContext ctx)
	{
		cleanDatastore();
	}

	@AfterMethod(alwaysRun=true)
	public void methodTeardown(final ITestContext ctx)
	{
		cleanDatastore();
	}

	@BeforeTest(alwaysRun=true)                                                                                                                                                                                                                                                                                                                                                                                                                    
	public void testSetup(final ITestContext context) throws IOException {
		setupPersistentStore(context);
	}

	@AfterTest(alwaysRun=true)
	public void testTeardown(final ITestContext context) {
		teardownPersistentStore(context);
	}
	
	@BeforeClass(alwaysRun=true)                                                                                                                                                                                                                                                                                                                                                                                                                    
	public void classSetup(final ITestContext context) throws IOException {
		setupPersistentStore(context);
	}

	/**
	 * Get a File instance for a file in the current output folder
	 * 
	 * @param name
	 * 
	 * @return
	 */

	protected File getFileInOutputFolder(final ITestContext ctx, final String name) {
		checkNotNull(ctx);
		checkNotNull(name);
		checkArgument(!name.isEmpty());
		
		File file = new File(ctx.getOutputDirectory() + StandardSystemProperty.FILE_SEPARATOR.value() + name);

		return file;
	}
	
	protected File getFileInCurrentWorkingFolder(final ITestContext ctx, final String name) {
		checkNotNull(ctx);
		checkNotNull(name);
		checkArgument(!name.isEmpty());

		File file = new File(ctx.getOutputDirectory() + StandardSystemProperty.FILE_SEPARATOR.value() + name);

		return file;
	}
	
	/**
	 * Method to be used in datastore clean up between methods that use the
	 * datastore
	 * 
	 * @param clazz
	 *            The class type to be removed from the datastore.
	 */

	protected <T> void deleteAnyStored(Class<T> clazz) {
		ObjectSet<T> toBeDeleted = (ObjectSet<T>) objStore.query(clazz);

		if (toBeDeleted.size() > 0) {
			for (T t : toBeDeleted) {
				objStore.delete(t);
			}

			objStore.commit();
		}
	}

	/**
	 * @return
	 * @throws Db4oIOException
	 * @see com.db4o.ObjectContainer#close()
	 */
	public boolean close() throws Db4oIOException {
		return objStore.close();
	}

	/**
	 * @throws Db4oIOException
	 * @throws DatabaseClosedException
	 * @throws DatabaseReadOnlyException
	 * @see com.db4o.ObjectContainer#commit()
	 */
	public void commit() throws Db4oIOException, DatabaseClosedException,
			DatabaseReadOnlyException {
		objStore.commit();
	}

	/**
	 * @param arg0
	 * @throws Db4oIOException
	 * @throws DatabaseClosedException
	 * @throws DatabaseReadOnlyException
	 * @see com.db4o.ObjectContainer#delete(java.lang.Object)
	 */
	public void delete(Object arg0) throws Db4oIOException,
			DatabaseClosedException, DatabaseReadOnlyException {
		objStore.delete(arg0);
	}

	/**
	 * @return
	 * @throws DatabaseClosedException
	 * @see com.db4o.ObjectContainer#query()
	 */
	public Query query() throws DatabaseClosedException {
		return objStore.query();
	}

	/**
	 * @param arg0
	 * @return
	 * @throws Db4oIOException
	 * @throws DatabaseClosedException
	 * @see com.db4o.ObjectContainer#query(java.lang.Class)
	 */
	public <TargetType> ObjectSet<TargetType> query(Class<TargetType> arg0)
			throws Db4oIOException, DatabaseClosedException {
		return objStore.query(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @throws Db4oIOException
	 * @throws DatabaseClosedException
	 * @see com.db4o.ObjectContainer#query(com.db4o.query.Predicate)
	 */
	public <TargetType> ObjectSet<TargetType> query(Predicate<TargetType> arg0)
			throws Db4oIOException, DatabaseClosedException {
		return objStore.query(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @throws Db4oIOException
	 * @throws DatabaseClosedException
	 * @see com.db4o.ObjectContainer#queryByExample(java.lang.Object)
	 */
	public <T> ObjectSet<T> queryByExample(Object obj) throws Db4oIOException,
			DatabaseClosedException {
		Preconditions.checkNotNull(obj);
		
		return objStore.queryByExample(obj);
	}

	/**
	 * @param arg0
	 * @throws DatabaseClosedException
	 * @throws DatabaseReadOnlyException
	 * @see com.db4o.ObjectContainer#store(java.lang.Object)
	 */
	public void store(Object obj) throws DatabaseClosedException,
			DatabaseReadOnlyException {
		Preconditions.checkNotNull(obj);
		
		objStore.store(obj);
	}

	protected void failIfTheseExceptionsAreNotEquivalent(Throwable expected, Throwable actual)
	{
		/*
		 * If one exception exists and the other doesn't 
		 */
		
		if (expected == null && actual != null) {
			
			fail(format(
					"An exception we didn't expect was thrown when creating an instance of the class to be tested. It was a {0}",
					actual.getClass()
							.getSimpleName()));
		} 
		
		/*
		 * If they are of different types
		 */
		
		if (!expected.getClass().isAssignableFrom(
				actual.getClass())) {
			
			fail(format(
					"An exception was thrown as expected when creating an instance of the test class but it was of a different type. We expected {0}, we got {1}",
					expected.getClass().getSimpleName(),actual.getClass().getSimpleName()));
			
		}
		
		/*
		 * If we made it this far, the exceptions are of the same type. Now to check on their contents.
		 */

		if ( (expected.getMessage() == null && actual.getMessage() != null ) || (expected.getMessage() != null && actual.getMessage() == null ))  {

			fail(format(
						"An expected exception was thrown when creating an instance of the class to be tesed but the contents were not as expected. We expected '{0}', we got '{1}'",
						Strings.nullToEmpty(expected.getMessage()), Strings.nullToEmpty(actual.getMessage())));
			
		}
		
		/*
		 * How close are the contents
		 */
		
		if (!actual.getMessage().startsWith(
					expected.getMessage().substring(0,
							expected.getMessage().length() - 1))) {
				fail(format(
						"An EXPECTED exception was thrown when creating an instance of class to be tested but the contents were not as expected. We expected the contents to look like this {0} and instead they were {1}",
						expected.getMessage(), actual.getMessage()));
		}

	}
}
