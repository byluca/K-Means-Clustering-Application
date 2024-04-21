<!DOCTYPE html>
<html>
<head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        color: #333;
        padding: 20px;
        margin: 0;
    }
    .container {
        max-width: 800px;
        margin: auto;
        background: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1, h2, h3 {
        color: #2c3e50;
    }
    code {
        background-color: #ecf0f1;
        color: #e74c3c;
        padding: 2px 5px;
        border-radius: 3px;
        font-family: monospace;
    }
    img {
        max-width: 100%;
        height: auto;
        display: block;
        margin: 20px auto;
    }
</style>
</head>
<body>
<div class="container">
    <h1>K-Means Clustering Application</h1>
    <p>This application provides a JavaFX-based graphical user interface for performing K-Means clustering on datasets. It allows users to input, process, and visualize clustering operations interactively.</p>
    
    <h2>Features</h2>
    <ul>
        <li>Interactive input for the number of clusters to initialize the clustering process.</li>
        <li>Execution of the clustering process and visualization of the clustering results.</li>
        <li>Functionality to save the results of the clustering operations to a file for later use.</li>
    </ul>
    
    <h2>Usage Instructions</h2>
    <ol>
        <li>Enter the number of clusters in the provided text field.</li>
        <li>Click <code>Initialize Clustering</code> to set up the cluster set.</li>
        <li>Press <code>Perform Clustering</code> to start the clustering process and view the results.</li>
        <li>Use the <code>Save ClusterSet</code> button to save the results to a file.</li>
    </ol>
    
    <h2>Application Interface Preview</h2>
    <img src="https://i.imgur.com/yG0OWI7.png" alt="K-Means Clustering GUI">
    
    <h2>Prerequisites</h2>
    <p>JavaFX SDK and JDK 11 or later are required to run this application. Ensure that JavaFX libraries are included in your project configuration.</p>
    
    <h2>License</h2>
    <p>This project is licensed under the MIT License - see the LICENSE file for details.</p>
    
    <h3>How to Contribute</h3>
    <p>If you would like to contribute to this project or suggest improvements, please open an issue or pull request on the project's GitHub repository.</p>
</div>
</body>
</html>
