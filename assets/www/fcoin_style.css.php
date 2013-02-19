<?php
header('Content-type: text/css');
session_start();
if (!isset ($_SESSION['color'])) $_SESSION['color'] = '#303030';
if (!isset ($_SESSION['color2'])) $_SESSION['color2'] = '#202020';
if (!isset ($_SESSION['color3'])) $_SESSION['color3'] = '#606060';
//else $color = $_SESSION['color'];
//echo $color, $_SESSION['color'];  ///testausgabe
?>

<p>body
  { padding: 0; margin 0; background-repeat: no-repeat; 
  color: #FFFFFF; 
  background-color: <?php echo $_SESSION['color2']; ?>;
  font-family: Courier New; 
  font-size: 22px;
  text-align: center;
  }
  
  .maindiv
  {
  background-color: <?php echo $_SESSION['color']; ?>;
  position: absolute;
  width: 320px;
  margin-left: -160px; 
  left: 50%;
  top: 0px;
  min-height: 400px;
  border: 0px solid <?php echo $_SESSION['color2']; ?>; 
  }
  
  input
  {
  border: 1px solid #707070; 
  color: white; 
  background-color: <?php echo $_SESSION['color2']; ?>;
  font-family: courier new; 
  font-size: 22px;
  text-align: center;
  }
  
  
  
  .sb
  {
  width: 220px; 
  height: 30px;
  white-space: nowrap; 
  font-size: 22px; 
  text-align: center;
  background-color: <?=$_SESSION['color3']?>;
  }
  
  .sb_klein
  {
  white-space: nowrap; 
  text-align: center;
  background-color: <?=$_SESSION['color3']?>;
  border: 1px solid #707070;
  }
  
  .butt
  {
  background-color: <?=$_SESSION['color3']?>;
  width: 218px; 
  height: 28px; 
  border: 1px solid #707070; 
  top: 0px;
  margin: auto;
  white-space: nowrap; 
  line-height:30px; 
  font-size: 22px; 
  text-align: center;
  }
  /*
  a
  {
  text-decoration: none; 
  color: white;
  }                            */
  
  textarea
  {
  border: 1px solid #707070; 
  background-color: <?php echo $_SESSION['color2']; ?>; 
  color: white; 
  max-width: 300px; 
  max-height: 100px; 
  width: 300px; 
  height: 100px; 
  min-height: 100px; 
  min-width: 300px; 
  font-size: 22px; 
  margin-left: 10px; 
  margin-right: 10px;
  }
  
  table
  {
  width: 320px;       /*    
  min-width: 320px;   */
  max-width: 320px;       
  height: 10px; 
  min-height: 10px;  
  background-color: #707070; 
  text-align: left;
  border-collapse: separate;
  border-spacing: 1px;
  margin-left:auto; 
  margin-right:auto;
  
  }
  
  td
  {               /*
  width: 5px;           */
  text-align: left; 
  vertical-align: center; 
  background-color: <?php echo $_SESSION['color2']; ?>; 
  font-size: 12px;
  word-wrap: break-word;
  }   
  
  p
  {
  margin-top: 11px;
  margin-bottom: 11px;
  }
  
  .kl_txt
  {
  margin: 2px;
  font-size: 12px;
  text-align: left;
  }
  
  .med_txt
  {
  margin: 3px;
  font-size: 18px;
  text-align: left;
  }
  
  .thead
  {
  text-align: center; 
  vertical-align: center; 
  background-color: <?php echo $_SESSION['color4']; ?>; 
  font-size: 10px;
  font-weight: bold;
  }
  
  .trechts
  {
  text-align: right; 
  vertical-align: center; 
  background-color: <?php echo $_SESSION['color2']; ?>; 
  font-size: 10px;
  word-wrap: no-wrap;
  }
  
  .tlinks
  {
  text-align: left; 
  vertical-align: center; 
  background-color: <?php echo $_SESSION['color2']; ?>; 
  font-size: 10px;
  font-weight: normal;
  word-wrap: break-word;
  }
  
  
  
  .inv
  {
  margin: -2px;      
  width: 10px;        /*
  min-width: 10px;     
  max-width: 10px;    */                   
  background-color: transparent; 
  float: left;
  
  }
  
  .inv_td
  {
  margin: -2px;       
  background-color: transparent; 
  
  }
  
  .input_at_table
  {
  border: 0px solid #707070; 
  /*color: white; */
  background-color: <?php echo $_SESSION['color2']; ?>;
  font-family: courier new; 
  font-size: 12px;
  text-align: left;     
  min-width: 10px;
  min-height: 10px;       
  float: left;
  }
  
  .input_at_table_sb
  {
  border: 1px solid #707070; 
  /*color: white; */
  background-color: <?php echo $_SESSION['color3']; ?>;
  font-family: courier new; 
  font-size: 8px;
  text-align: center;
  height: 10px;
  width: 20px;
  padding-left: 0px;
  padding-right: 0px;
  padding-top: 0px;
  padding-bottom: 0px;
  
  }
  
  
  
  .td_at_cont
  {                   /*
  min-width: 10px;
  max-width: 100px;   */
  }
  
  
  .at_tab_div
  {
  background-color: <?php echo $_SESSION['color']; ?>;
  position: relative;  /*
  width: 250%;           
  margin-left: 50%;    
  left: -100%;            */
  top: 70px;
  min-height: 400px;
  border: 0px solid <?php echo $_SESSION['color2']; ?>;
  }</p>
